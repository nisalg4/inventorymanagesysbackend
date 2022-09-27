package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;


@Service
public class AssetService  {

    @Autowired
    AssetRepository assetRepo;


    public Asset getAssetById(String id) {
        return assetRepo.findByAssetId(id).get();
    }


    public Asset getAssetByName(String name) {
        return assetRepo.findByName(name);
    }

    public Asset addAsset(Asset asset) {
        try {
            if (this.getAssetById(asset.getAssetId()).getAssetId().equals(asset.getAssetId())) {
                return new Asset(null,null);
            } else {
                return assetRepo.save(asset);
            }

        }catch(NoSuchElementException e){
            return assetRepo.save(asset);
        }
    }
}

