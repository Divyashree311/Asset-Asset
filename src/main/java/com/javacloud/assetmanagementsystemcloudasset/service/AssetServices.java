package com.javacloud.assetmanagementsystemcloudasset.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.javacloud.assetmanagementsystemcloudasset.dto.Assets;

public interface AssetServices {
	
	public List<Assets> getAllAssets();
	
	public Assets getAssetById(int asset_id);
	
	public boolean deleteAsset(int asset_id);
	
	public Assets saveAsset(Assets assets);
	
	public Page<Assets> getAssets(int pageNo, int assetsPerPage);
	
	public Page<Assets> getSortedAssets(int pageNo, int assetsPerPage,String assetFieldName);

}
