package com.example.travel.service.impl;

import com.example.travel.dao.CityDao;
import com.example.travel.dao.RegionTabDao;
import com.example.travel.dao.TravelDestinationDao;
import com.example.travel.entity.City;
import com.example.travel.entity.RegionTab;
import com.example.travel.entity.TravelDestination;
import com.example.travel.service.PopulardestinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PopulardestinationsServiceImpl implements PopulardestinationsService {

    @Autowired
    private RegionTabDao regionTabDao;

    @Autowired
    private TravelDestinationDao travelDestinationDao;

    @Autowired
    private CityDao cityDao;

    // RegionTab相关方法实现
    @Override
    public RegionTab getRegionTabById(Integer id) {
        return regionTabDao.findById(id);
    }

    @Override
    public List<RegionTab> getAllRegionTabs() {
        return regionTabDao.findAll();
    }

    @Override
    public RegionTab getRegionTabByName(String name) {
        return regionTabDao.findByName(name);
    }

    @Override
    public int addRegionTab(RegionTab regionTab) {
        return regionTabDao.insert(regionTab);
    }

    @Override
    public int updateRegionTab(RegionTab regionTab) {
        return regionTabDao.update(regionTab);
    }

    @Override
    public int deleteRegionTab(Integer id) {
        return regionTabDao.delete(id);
    }

    @Override
    public int countRegionTabs() {
        return regionTabDao.count();
    }

    // TravelDestination相关方法实现
    @Override
    public TravelDestination getTravelDestinationById(Integer id) {
        return travelDestinationDao.findById(id);
    }

    @Override
    public List<TravelDestination> getAllTravelDestinations() {
        return travelDestinationDao.findAll();
    }

    @Override
    public List<TravelDestination> getTravelDestinationsByRegionId(Integer regionId) {
        return travelDestinationDao.findByRegionId(regionId);
    }

    @Override
    public List<TravelDestination> getTravelDestinationsByRegionName(String regionName) {
        return travelDestinationDao.findByRegionName(regionName);
    }

    @Override
    public int addTravelDestination(TravelDestination travelDestination) {
        return travelDestinationDao.insert(travelDestination);
    }

    @Override
    public int updateTravelDestination(TravelDestination travelDestination) {
        return travelDestinationDao.update(travelDestination);
    }

    @Override
    public int deleteTravelDestination(Integer id) {
        return travelDestinationDao.delete(id);
    }

    @Override
    public int countTravelDestinations() {
        return travelDestinationDao.count();
    }

    @Override
    public int countTravelDestinationsByRegionName(String regionName) {
        return travelDestinationDao.countByRegionName(regionName);
    }

    // City相关方法实现
    @Override
    public City getCityById(Integer id) {
        return cityDao.findById(id);
    }

    @Override
    public List<City> getAllCities() {
        return cityDao.findAll();
    }

    @Override
    public List<City> getCitiesByDestinationId(Integer destinationId) {
        return cityDao.findByDestinationId(destinationId);
    }

    @Override
    public List<City> getCitiesByRegionName(String regionName) {
        return cityDao.findByRegionName(regionName);
    }

    @Override
    public int addCity(City city) {
        return cityDao.insert(city);
    }

    @Override
    public int updateCity(City city) {
        return cityDao.update(city);
    }

    @Override
    public int deleteCity(Integer id) {
        return cityDao.delete(id);
    }

    @Override
    public int countCities() {
        return cityDao.count();
    }

    @Override
    public int countCitiesByDestinationId(Integer destinationId) {
        return cityDao.countByDestinationId(destinationId);
    }

    @Override
    public int batchAddCities(List<City> cities) {
        return cityDao.batchInsert(cities);
    }

    // 综合查询方法实现
    @Override
    public List<RegionTab> getRegionTabsWithDestinations() {
        return regionTabDao.findAll(); // findAll方法已经包含关联查询
    }

    @Override
    public List<TravelDestination> getTravelDestinationsWithCities() {
        return travelDestinationDao.findAll(); // findAll方法已经包含关联查询
    }

    @Override
    public List<City> getCitiesWithDestinationAndRegion() {
        return cityDao.findAll(); // findAll方法已经包含关联查询
    }
}