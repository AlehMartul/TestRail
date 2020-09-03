package framework.tools;

import java.io.IOException;

public class TestRailApiTools {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String PROPERTIES = "config.properties";
    private static final String URI = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("UriApi");
    static String ADD_RESULT = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("addResult");
    static String ADD_ATTACH = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("addAttach");
    static String TEST_ID = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("testId");
    static String RESULT_ID = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("resultId");
    static String BASE_URI = String.format(URI, ADD_RESULT, TEST_ID);
    static String BASE_URI_FOR_ATTACHMENT = String.format(URI, ADD_ATTACH, RESULT_ID);
    static String LOGIN = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("login");
    static String PASSWORD = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("password");
    static String BODY = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("body");
    static String NAME_OF_SCREENSHOT = new ReadPropertyTool(TestRailApiTools.RESOURCES_PATH, TestRailApiTools.PROPERTIES)
            .getProperty("screenshot");


    public static void addResultToRun (String result){
        ApiTool apiTool = new ApiTool();
        apiTool.postResult(BASE_URI, LOGIN, PASSWORD, String.format(BODY, result));
    }

    public static void addAttachmentToResult () throws IOException {
        OtherTools.takeScreenshot(NAME_OF_SCREENSHOT);
        ApiTool apiTool = new ApiTool();
        apiTool.postScreenshot(BASE_URI_FOR_ATTACHMENT, LOGIN, PASSWORD, NAME_OF_SCREENSHOT);
    }
}