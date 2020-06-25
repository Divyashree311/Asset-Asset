package com.javacloud.assetmanagementsystemcloudasset.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacloud.assetmanagementsystemcloudasset.dto.Assets;
import com.javacloud.assetmanagementsystemcloudasset.exceptions.AssetNotFoundExceptions;
import com.javacloud.assetmanagementsystemcloudasset.response.Response;
import com.javacloud.assetmanagementsystemcloudasset.service.AssetServices;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class AssestController {
	
	private AssetServices assetServices;
	
	@Autowired
	public AssestController(AssetServices assetServices) {
		this.assetServices=assetServices;
	}
	
	@GetMapping("/assets")
	
	public Response<List<Assets>> getAllAssets(){
		List<Assets> assests = assetServices.getAllAssets();
		
		
		return new Response<>(false , "list retrieved", assests);
	}
	
	@GetMapping("/assets/getassets/{asset_id}")
	public Response<Assets> getAssetById(@PathVariable int asset_id) {
		Assets assets = assetServices.getAssetById(asset_id);
		
		if (assets == null) {
			throw new AssetNotFoundExceptions("assets id not found " + asset_id);
		}
		return new Response<Assets>(true,"assets found",assets);
		
		
	}
	
	@DeleteMapping("/assets/deleteasset/{asset_id}")
	public Response<Assets> deleteAsset(@PathVariable int asset_id) {
		Assets assets = assetServices.getAssetById(asset_id);
		
		if(assets != null) {
			assetServices.deleteAsset(asset_id);
			return new Response<Assets>(false, "Deleted ", assets);
		} else {
			return new Response<Assets>(true,"Delete failed",null);
		}
	
	}
	
	@PutMapping("/assets/updateasset")
	public Response<Assets> updateAsset(@RequestBody Assets assets) {
		Assets assets2 = assetServices.saveAsset(assets);
		
		
		if(assets2 != null) {
			assetServices.saveAsset(assets2);
			return new Response<Assets>(false, "Updated succesfully ", assets2);
		} else {
			return new Response<Assets>(true,"Update failed",null);

		}
		
		
	}
	
	@PostMapping("/assets/addasset")
	public Response<Assets> addAsset(@Valid @RequestBody Assets assets) {
		
		Assets assets2 = assetServices.saveAsset(assets);
		
		if(assets2 != null ) {
			return new Response<Assets>(false, "successfully saved", assets2);
		} else {
			return new Response<Assets>(true,"save failed",null);
		}
		
	}
	
	@GetMapping("/assets/{pageNo}/{assetsPerPage}")
	public Page<Assets> getAssets(@PathVariable int pageNo, @PathVariable int assetsPerPage) {
		return assetServices.getAssets(pageNo, assetsPerPage);
	
	}

	@GetMapping("/assets/{pageNo}/{assetsPerPage}/{assetFieldName}")
	public Page<Assets> getSortedAssets(@PathVariable int pageNo, @PathVariable int assetsPerPage,@PathVariable String assetFieldName){
		return assetServices.getSortedAssets(pageNo, assetsPerPage, assetFieldName);
	}
}
