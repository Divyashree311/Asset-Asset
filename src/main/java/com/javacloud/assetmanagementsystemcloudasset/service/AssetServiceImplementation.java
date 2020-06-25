package com.javacloud.assetmanagementsystemcloudasset.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javacloud.assetmanagementsystemcloudasset.dto.Assets;
import com.javacloud.assetmanagementsystemcloudasset.repository.AssetRepository;

@Service
public class AssetServiceImplementation implements AssetServices{

	private AssetRepository assetRepository;
	
	@Autowired
	public AssetServiceImplementation(AssetRepository assetRepository) {
		this.assetRepository = assetRepository;
	}
	
	@Override
	public List<Assets> getAllAssets() {
		return assetRepository.findAll();
	}

	@Override
	public Assets getAssetById(int asset_id) {
		
		Optional<Assets> result = assetRepository.findById(asset_id);
		Assets assets;
		
		if(result.isPresent()) {
			assets=result.get();
		} else {
			throw new RuntimeException("Asset " +  asset_id  + "id not found");
		}
		
		return assets;
	}

	@Override
	public boolean deleteAsset(int asset_id) {	
		assetRepository.deleteById(asset_id);
		return true;
	}

	@Override
	public Assets saveAsset(Assets assets) {
		
		return assetRepository.save(assets);
	}

	@Override
	public Page<Assets> getAssets(int pageNo, int assetsPerPage) {
		
		Pageable pageable = PageRequest.of(pageNo, assetsPerPage);
		return assetRepository.findAll(pageable);
	}

	@Override
	public Page<Assets> getSortedAssets(int pageNo, int assetsPerPage, String assetFieldName) {
		Pageable pageable = PageRequest.of(pageNo, assetsPerPage, Sort.by(assetFieldName));
		return assetRepository.findAll(pageable);
	}

}
