package com.example.travel.service;

import com.example.travel.entity.City;
import com.example.travel.entity.RegionTab;
import com.example.travel.entity.TravelDestination;

import java.util.List;

public interface PopulardestinationsService {
    
    // RegionTab相关方法
    RegionTab getRegionTabById(Integer id);
    List<RegionTab> getAllRegionTabs();
    RegionTab getRegionTabByName(String name);
    int addRegionTab(RegionTab regionTab);
    int updateRegionTab(RegionTab regionTab);
    int deleteRegionTab(Integer id);
    int countRegionTabs();
    
    // TravelDestination相关方法
    TravelDestination getTravelDestinationById(Integer id);
    List<TravelDestination> getAllTravelDestinations();
    List<TravelDestination> getTravelDestinationsByRegionId(Integer regionId);
    List<TravelDestination> getTravelDestinationsByRegionName(String regionName);
    int addTravelDestination(TravelDestination travelDestination);
    int updateTravelDestination(TravelDestination travelDestination);
    int deleteTravelDestination(Integer id);
    int countTravelDestinations();
    int countTravelDestinationsByRegionName(String regionName);
    
    // City相关方法
    City getCityById(Integer id);
    List<City> getAllCities();
    List<City> getCitiesByDestinationId(Integer destinationId);
    List<City> getCitiesByRegionName(String regionName);
    int addCity(City city);
    int updateCity(City city);
    int deleteCity(Integer id);
    int countCities();
    int countCitiesByDestinationId(Integer destinationId);
    int batchAddCities(List<City> cities);
    
    // 综合查询方法
    List<RegionTab> getRegionTabsWithDestinations();
    List<TravelDestination> getTravelDestinationsWithCities();
    List<City> getCitiesWithDestinationAndRegion();
}