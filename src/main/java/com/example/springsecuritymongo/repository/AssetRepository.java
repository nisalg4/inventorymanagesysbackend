package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface AssetRepository extends MongoRepository<Asset, String> {
    Optional<Asset> findByAssetId(String assetId);
    Asset findByName(String name);
    Asset save(Asset asset);
}