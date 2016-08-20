package me.utlight.small.controllers;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.utlight.small.entities.Goods;
import me.utlight.small.services.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	GoodsService goodsService;
	
	
	//��Ʒ�б�
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required=false,defaultValue="1")int pageNO){
		int size = 5;
		model.addAttribute("size",size);
		model.addAttribute("pageNO", pageNO);
		model.addAttribute("count",goodsService.getGoodsCount());
		model.addAttribute("goods", goodsService.getGoodsPager(pageNO, size));
		
		return "/goods/list";
	}
	
	//����ɾ��
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id, @RequestParam(required=false,defaultValue="1")int pageNO, RedirectAttributes redirectAttributes){
		
		if (goodsService.delete(id) > 0) {
			redirectAttributes.addFlashAttribute("message", "ɾ���ɹ�!");
		}else {
			redirectAttributes.addFlashAttribute("message", "ɾ��ʧ�ܣ�");
		}
		
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	//����ɾ��
	@RequestMapping("/deletes")
	public String deletes(Model model, @RequestParam int[] id, @RequestParam(required=false,defaultValue="1")int pageNO, RedirectAttributes redirectAttributes){
		
		int rows = goodsService.deletes(id);
		if (rows > 0) {
			redirectAttributes.addFlashAttribute("message", "ɾ��"+rows+"�м�¼�ɹ���");
		}else {
			redirectAttributes.addFlashAttribute("message", "ɾ��ʧ�ܣ�");
		}
		
		return "redirect:/goods/list?pageNO="+pageNO;
	}
	
	//�����Ʒ get
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("entity", new Goods());
		return "/goods/add";
	}
	
	//�����Ʒ post
	@RequestMapping("/addSave")
	public String addSave(Model model, @ModelAttribute("entity")@Valid Goods entity, BindingResult bindingresult){
		
		if(!bindingresult.hasErrors()){
			if(goodsService.add(entity) > 0){
				return "redirect:/goods/list";
			}
			
		}
		
		model.addAttribute("entity", entity);
		return "/goods/add";
	}
	
	//�༭��Ʒ get
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id){
		model.addAttribute("entity", goodsService.getGoodsById(id));
		return "/goods/edit";
	}
	
	//�༭��Ʒ post
	@RequestMapping("/editSave")
	public String editSave(Model model, @ModelAttribute("entity")@Valid Goods entity, BindingResult bindingResult){
		
		if(!bindingResult.hasErrors()){
			if(goodsService.update(entity) > 0){
				return "redirect:list";
			}
		}
		model.addAttribute("entity", entity);
		return "/goods/edit";
	}
	
	//ͼƬ�ϴ� get
	@RequestMapping("/upPicture/{id}")
	public String upPicture(Model model, @PathVariable int id){
		model.addAttribute("entity", goodsService.getGoodsById(id));
		return "/goods/upfile";
	}
	
	//ͼƬ�ϴ� post
	@RequestMapping("/upPictureSave/{id}")
	public String upPictureSave(Model model, @PathVariable int id, MultipartFile picFile,HttpServletRequest rquest){
		
		Goods entity = goodsService.getGoodsById(id);
		if (picFile != null) {
			if (picFile.getSize() > 0) {
				String path = rquest.getServletContext().getRealPath("/images");
				String filename = UUID.randomUUID().toString()+"_"+picFile.getOriginalFilename().substring(picFile.getOriginalFilename().lastIndexOf("."));
				File tempFile = new File(path, filename);
				
				try {
					//�����ļ�
					picFile.transferTo(tempFile);
					//��������
					entity.setPicture(filename);
					goodsService.update(entity);
					
					return "redirect:/goods/list";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("entity", entity);
		return "/goods/upfile";
	}
	
	
}
