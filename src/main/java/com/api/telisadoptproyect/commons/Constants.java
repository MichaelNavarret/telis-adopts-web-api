package com.api.telisadoptproyect.commons;

public class Constants {
    public static final String PASS_REGEX = "^(?=(?:.*[A-Z]))(?=(?:.*[a-z]))(?=(?:.*\\d))(?=(?:.*[!@#$%^&*()\\-_=+{};:,<.>]))([A-Za-z0-9!@#$%^&*()\\-_=+{};:,<.>]{8,20})$";
    public static final String EMAIL_REGEX = "^(([^<>()[\\\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public static final String CLOUDINARY_MAIN_FOLDER_PATH = "teliwis_adopts";
    public static final String CLOUDINARY_SPECIES_FOLDER_PATH = CLOUDINARY_MAIN_FOLDER_PATH + "/species";
    public static final String CLOUDINARY_TRAITS_SHEET_FOLDER_PATH = CLOUDINARY_SPECIES_FOLDER_PATH + "/traits_sheet";
    public static final String CLOUDINARY_LOGO_FOLDER_PATH = CLOUDINARY_SPECIES_FOLDER_PATH + "/logo";
    public static final String CLOUDINARY_MASTER_LIST_BANNER_FOLDER_PATH = CLOUDINARY_SPECIES_FOLDER_PATH + "/master_list_banner";
    public static final String CLOUDINARY_SPECIE_FORM_FOLDER_PATH = CLOUDINARY_SPECIES_FOLDER_PATH + "/forms";
    public static final String CLOUDINARY_GUIDE_SHEET_FOLDER_PATH = CLOUDINARY_SPECIES_FOLDER_PATH + "/guide_sheet";
    public static final String CLOUDINARY_ADOPTS_FOLDER_PATH = CLOUDINARY_MAIN_FOLDER_PATH + "/adopts";
    public static final String CLOUDINARY_ADOPTS_ICONS_FOLDER_PATH = CLOUDINARY_ADOPTS_FOLDER_PATH + "/icons";
}
