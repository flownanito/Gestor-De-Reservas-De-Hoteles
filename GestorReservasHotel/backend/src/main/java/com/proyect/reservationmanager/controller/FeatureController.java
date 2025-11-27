package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.model.Feature;
import com.proyect.reservationmanager.repository.FeatureRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
  private final FeatureRepository featureRepository;

  public FeatureController(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  // ---------------------- GET ALL ----------------------
  @GetMapping
  public ResponseEntity<List<Feature>> getAllFeatures() {
    List<Feature> features = featureRepository.findAll();
    return new ResponseEntity<>(features, HttpStatus.OK);
  }

  // ---------------------- GET BY ID ----------------------
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFeatureById(@PathVariable Long id) {
    Optional<Feature> feature = featureRepository.findById(id);

    if (feature.isPresent()) {
      return new ResponseEntity<>(feature.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Feature not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- GET BY NAME ----------------------
  @GetMapping("/name/{featureName}")
  public ResponseEntity<Object> getFeatureByName(@PathVariable String featureName) {
    Optional<Feature> feature = featureRepository.findByFeatureName(featureName);

    if (feature.isPresent()) {
      return new ResponseEntity<>(feature.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Feature with name " + featureName + " not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- CREATE ----------------------
  @PostMapping
  public ResponseEntity<Object> createFeature(@Valid @RequestBody Feature feature) {
    if (featureRepository.findByFeatureName(feature.getFeatureName()).isPresent()) {
      return new ResponseEntity<>("Error, la caracteristica ya existe", HttpStatus.CONFLICT);
    }

    Feature savedFeature = featureRepository.save(feature);
    return new ResponseEntity<>(savedFeature, HttpStatus.CREATED);
  }

  // ---------------------- UPDATE ----------------------
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateFeature(@PathVariable Long id, @Valid @RequestBody Feature featureDetails) {
    Optional<Feature> featureOpt = featureRepository.findById(id);

    if (featureOpt.isPresent()) {
      if (featureRepository.findByFeatureNameAndIdNot(featureDetails.getFeatureName(), id).isPresent()) {
        return new ResponseEntity<Object>("Error 409", HttpStatus.CONFLICT);
      }

      Feature feature = featureOpt.get();
      feature.setFeatureName(featureDetails.getFeatureName());
      Feature updatedFeature = featureRepository.save(feature);
      return new ResponseEntity<Object>(updatedFeature, HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Feature with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- DELETE ----------------------
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
    if (featureRepository.existsById(id)) {
      featureRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
