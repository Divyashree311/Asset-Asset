package com.javacloud.assetmanagementsystemcloudasset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacloud.assetmanagementsystemcloudasset.dto.Assets;

public interface AssetRepository extends JpaRepository<Assets,Integer>{

}
