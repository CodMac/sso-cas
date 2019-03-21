package server.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    private static JacksonUtil DEFAULT_INSTANCE = new JacksonUtil(null);
    private static JacksonUtil DATETIME_INSTANCE = new JacksonUtil("yyyy-MM-dd HH:mm:ss");
    private static JacksonUtil DATE_INSTANCE = new JacksonUtil("yyyy-MM-dd");
    private static JacksonUtil TIME_INSTANCE = new JacksonUtil("HH:mm:ss");

    private ObjectMapper mapper;

    private JacksonUtil(String dateformat) {
        init(dateformat);
    }

    private void init(String dateformat) {
        mapper = new ObjectMapper();
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        if (StringUtils.isNotBlank(dateformat)) {
            mapper.setDateFormat(new SimpleDateFormat(dateformat));
        }
    }

    /**
     * Get the JacksonUtil instance with default ObjectMapper.
     *
     * @return a JacksonUtil instance
     */
    public static JacksonUtil defaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /**
     * Get the JacksonUtil instance which will handle java.util.Date with the format string "yyyy-MM-dd HH:mm:ss"
     *
     * @return a JacksonUtil instance
     */
    public static JacksonUtil datetimeInstance() {
        return DATETIME_INSTANCE;
    }

    /**
     * Get the JacksonUtil instance which will handle java.util.Date with the format string "yyyy-MM-dd"
     *
     * @return a JacksonUtil instance
     */
    public static JacksonUtil dateInstance() {
        return DATE_INSTANCE;
    }

    /**
     * Get the JacksonUtil instance which will handle java.util.Date with the format string "HH:mm:ss"
     *
     * @return a JacksonUtil instance
     */
    public static JacksonUtil timeInstance() {
        return TIME_INSTANCE;
    }

    /**
     * Get a new JacksonUtil instance which will handle java.util.Date with specified datetime format
     *
     * @param dateformat the specified datetime format
     * @return a JacksonUtil instance
     */
    public static JacksonUtil customFormatInstance(String dateformat) {
        return new JacksonUtil(dateformat);
    }

    /**
     * 仅读取整个json中的某个属性值
     *
     * @param jsonString json string
     * @param fieldName field name
     * @return the field value
     * @throws IOException
     */
    public String readField(String jsonString, String fieldName) throws IOException {
        JsonNode root = mapper.readTree(jsonString);
        JsonNode node = root.path(fieldName);
        return node.asText();
    }

    /**
     * 将json字符串转换为指定类型的java对象
     * 
     * @param jsonString
     * @param clazz
     * @return
     * @throws IOException
     */
    public <T> T json2pojo(String jsonString, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonString, clazz);
    }

    /**
     * 将json字符串转换为HashMap(json里的子对象也将转换为Map)
     * 
     * @param jsonString
     * @return
     * @throws IOException
     */
    public Map<String, Object> json2map(String jsonString) throws IOException {
        return mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {
        });
    }
    
    /**
     * 将json字符串转换为ArrayList
     * 
     * @param jsonString
     * @return
     * @throws IOException
     */
    public ArrayList< Object> json2List(String jsonString) throws IOException {
    	return mapper.readValue(jsonString, new TypeReference<ArrayList<Object>>() {
    	});
    }
    
    /**
     * 将java对象转换为json字符串
     * 
     * @param pojo
     * @return
     * @throws JsonProcessingException
     */
    public String pojo2json(Object pojo) throws JsonProcessingException {
        return mapper.writeValueAsString(pojo);
    }

    /**
     * 将java对象转换为map对象
     * @param pojo
     * @return
     * @throws IOException
     */
    public Map<String, Object> pojo2map(Object pojo) throws IOException {
        return json2map(pojo2json(pojo));
    }

	
}
