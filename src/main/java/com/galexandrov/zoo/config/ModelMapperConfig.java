package com.galexandrov.zoo.config;


import com.galexandrov.zoo.dtos.BiletCreateModel;
import com.galexandrov.zoo.dtos.BiletCreateServiceModel;
import com.galexandrov.zoo.dtos.GuideCreateModel;
import com.galexandrov.zoo.dtos.GuideCreateServiceModel;
import com.galexandrov.zoo.models.Bilet;
import com.galexandrov.zoo.models.Ekskurzovod;
import com.galexandrov.zoo.models.Guide;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        initMapper(mapper);
        initMap(mapper);
//        initMapSubject(mapper);
    }

    private static void initMapper(ModelMapper mapper) {
        Converter<String, Bilet> stringToBiletConverter =
                ctx -> Bilet.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(BiletCreateModel.class, BiletCreateServiceModel.class)
                .addMappings(map -> map
                        .using(stringToBiletConverter)
                        .map(
                                BiletCreateModel::getBilet,
                                BiletCreateServiceModel::setBilet
                        )
                );
    }

    private static void initMap(ModelMapper mapper) {
        Converter<String, Ekskurzovod> stringToGuideConverter =
                ctx -> Ekskurzovod.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(GuideCreateModel.class, GuideCreateServiceModel.class)
                .addMappings(map -> map
                        .using(stringToGuideConverter)
                        .map(
                                GuideCreateModel::getEkskurzovod,
                                GuideCreateServiceModel::setEkskurzovod
                        )
                );
    }

//    private static void initMap(ModelMapper mapper) {
//        Converter<String, Vid> stringVidConverter =
//                ctx -> Vid.valueOf(ctx.getSource().toUpperCase());
//
//        mapper.createTypeMap(GroupModel.class, GroupServiceModel.class)
//                .addMappings(map -> map
//                        .using(stringVidConverter)
//                        .map(
//                                GroupModel::getVid,
//                                GroupServiceModel::setVid
//                        )
//                );
//    }
//
//    private static void initMapSubject(ModelMapper mapper) {
//        Converter<String, Education> stringToEducationConverter =
//                ctx -> Education.valueOf(ctx.getSource().toUpperCase());
//
//        mapper.createTypeMap(SubjectModel.class, SubjectServiceModel.class)
//                .addMappings(map -> map
//                        .using(stringToEducationConverter)
//                        .map(
//                                SubjectModel::getEducation,
//                                SubjectServiceModel::setEducation
//                        )
//                );
//    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }
}
