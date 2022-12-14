package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface AssetRepository extends MongoRepository<Asset, String> {
    Optional<Asset> findAssetByAssetId(String assetId);
    Optional<Asset> findAssetByName(String name);

    Boolean existsByName(String name);
    Asset save(Asset asset);
    Asset deleteAssetByAssetId(String id);



    @Override
    List<Asset> findAll();
}