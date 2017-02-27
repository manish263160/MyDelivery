package com.mydelivery.utils;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String DUPLICATE = "duplicate";
	public static final String IMAGE_FOLDER = "imageFolder";
	public static final String APP_PATH = "appPath";
	public static final String PROFILE_FOLDER = "profileFolder";
	public static final String TEAM_FOLDER = "/team/";
	public static final String ACHIEVEMENT_FOLDER = "/achievement/";
	public static final String PRODUCT_FOLDER = "/product/";
	public static final String PRESS_RELEASE_FOLDER = "/pressRelease/";
	public static final String IMAGE_TYPE_DEFAULT="default";
	public static final String THUMBNAIL_IMAGE_ASPECT_RATIO="default_thumbnail_image_aspect_ratio";
	public static final String COMPANY_NAME="Freelancer";
	public static final String MESSAGE_ATTACHMENT="messageAttachment/";
	public static final String MEDIA_IMG_FOLDER="/media/images/";
	public static final String EVENT_FOLDER="/event/";
	public static final String OFFERING_PRODUCT_PHOTOS="/offering/product/";
	public static final String VIDEO_OFFERING="/offerVideo/";
	public static final String OFFERING_ONLINE_COURSE_PHOTOS="/offering/onlineCourse/";
	public static final String OFFERING_EDUCATION_FULL_TIME_PHOTOS="/offering/educationFullTime/";
	public static final String OFFERING_EDUCATION_PART_TIME_PHOTOS="/offering/educationPartTime/";
	public static final String PROFILE_IMG_FOLDER = "/profile/";
	public static final String OFFERING_SERVICE_PHOTOS="/offering/service/";
	public static final String NEED_COUNT = "need_count";
	public static final String OFFER_COUNT = "offer_count";
	public static final String ATTRIBUTE_MAIN_NAV_JOBS = "jobs";
	public static final String ATTRIBUTE_MAIN_NAV = "main_nav";
	public static final String GET_NO_OF_MESSAGES_COUNT="message_count";
	public static final String ATTRIBUTE_MAIN_NAV_ORGANIZATION="organization";
	public static final String MAIL_TEMPLATE_PATH="mailTemplatePath";
	public static final String NEED_SERVICE_PHOTOS="/need/service/";
	public static final String NEED_OFFER_OTHER="/needOfferOther/";
	public static final int IMG_WIDTH = 242;
	public static final int IMG_HEIGHT = 242;
	public static final int SMALL_IMG_WIDTH = 38;
	public static final int SMALL_IMG_HEIGHT = 38;
	public static final String TOMORROW_ONE_TEAM_NAME = "Team TomorrowsOne";
	public static final String REPORT_USER_THRESHOLD="reportUserThreshold";
	public static final String INVITE_SUBJECT="Invitation to join TomorrowsOne.com";

	
	public static final Map<String, String> solrHighlighting = new HashMap<String, String>() {{
		put("name_suggest","Name");
		put("userSkills","Skill");
		put("currentTitle","Current Title");
		put("currentCompany","Current Company");
		put("pastCompany","Past Company");
		put("college_name","College Name");
		put("university","University");
		put("degree","Degree");
		put("study_field","Study Field");
		put("certification_name","Certification");
		put("user_language_name","Language Known");
		put("interests_name","Interest");
		put("org_search","Organisation Name");
		put("offer_product_name_suggest","Product Offering");
		put("offer_service_name_suggest","Service Offering");
		put("need_product_name_suggest","Product Looking For");
		put("need_service_name_suggest","Service Looking For");
		put("n_v_topic","Video Topic Looking For");
		put("n_v_upload_type","Video Type Looking For");
		put("o_v_topic","Video Topic Offering");
		put("o_v_upload_type","Video Type Offering");
		put("n_e_f_course_name","Course FullTime Looking For");
		put("n_e_f_institute_name","Intitute Looking For");
		put("o_e_f_course_name","Course FullTime Offering");
		put("o_e_f_institute_name","Intiture Offering");
		put("n_e_p_course_name","Course PartTime Looking For");
		put("n_e_p_institute_name","Institute PartTime Looking For");
		put("o_e_p_course_name","Course Name PartTime Offering");
		put("o_e_p_institute_name","Institute PartTime");
		put("n_course_name","Couse Looking For");
		put("n_o_c_institute_name","Institute Looking For");
		put("o_course_name","Course Name Offering");
		put("o_o_c_institute_name","Institure Offering");
		put("n_need_offer_other_name","");
		put("o_need_offer_other_name","");
	}};
	//.put("userSkills","Skill").put("currentTitle","Current Title").put("currentCompany","Current Company").put("pastCompany","Past Company").put("college_name","College Name").put("university","University").put("degree","Degree").put("study_field","Study Field").put("certification_name","Certification").put("user_language_name","Language Known").put("interests_name","Interest").put("org_search","Organisation Name").put("offer_product_name_suggest","Product Offering").put("offer_service_name_suggest","Service Offering").put("need_product_name_suggest","Product Looking For").put("need_service_name_suggest","Service Looking For").put("n_v_topic","Video Topic Looking For").put("n_v_upload_type","Video Type Looking For").put("o_v_topic","Video Topic Offering").put("o_v_upload_type","Video Type Offering").put("n_e_f_course_name","Course FullTime Looking For").put("n_e_f_institute_name","Intitute Looking For").put("o_e_f_course_name","Course FullTime Offering").put("o_e_f_institute_name","Intiture Offering").put("n_e_p_course_name","Course PartTime Looking For").put("n_e_p_institute_name","Institute PartTime Looking For").put("o_e_p_course_name","Course Name PartTime Offering").put("o_e_p_institute_name","Institute PartTime").put("n_course_name","Couse Looking For").put("n_o_c_institute_name","Institute Looking For").put("o_course_name","Course Name Offering").put("o_o_c_institute_name","Institure Offering").put("n_need_offer_other_name","").put("o_need_offer_other_name","");
}
