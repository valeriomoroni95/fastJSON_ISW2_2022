package fastJSON_ISW2;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;

/*
 Test in order of appearance:
 1) test_0
 2) test_1
 3) test_2
 4) test_cast_Integer
 5) test_cast_Integer_2
 6) test_cast_to_long
 7) test_cast_to_Long
 8) test_cast_to_short
 9) test_cast_to_Short
 10) test_cast_to_byte
 11) test_cast_to_Byte
 12) test_cast_to_BigInteger
 13) test_cast_to_BigDecimal
 14) test_cast_to_boolean
 15) test_cast_to_Boolean
 16) test_cast_null
 17) test_cast_to_String
 18) test_cast_to_Date
 19) test_cast_to_SqlDate
 20) test_cast_to_SqlDate_string
 21) test_cast_to_SqlDate_null
 22) test_cast_to_SqlDate_null2
 23) test_cast_to_SqlDate_util_Date
 24) test_cast_to_SqlDate_sql_Date
 25) test_cast_to_SqlDate_sql_Date2
 26) test_cast_to_SqlDate_calendar
 27) test_cast_to_SqlDate_error
 28) test_cast_to_Timestamp
 29) test_cast_to_Timestamp_string
 30) test_cast_to_Timestamp_number
 31) test_cast_to_Timestamp_null
 32) test_cast_to_Timestamp_null2
 33) test_cast_to_Timestamp_1970_01_01_00_00_00
 34) test_cast_to_BigDecimal_same
 35) test_cast_to_BigInteger_same
 36) test_cast_Array
 37) test_cast_to_Timestamp_util_Date
 38) test_cast_to_Timestamp_sql_Date
 39) test_cast_to_Timestamp_sql_Timestamp
 40) test_cast_to_Timestamp_calendar
 41) test_cast_to_Timestamp_not_error
 42) test_cast_ab
 43) test_cast_ab_1
 44) test_cast_ab_error
 45) test_error
 46) test_error_2
 47) test_3
 */

@RunWith(Parameterized.class)
@SuppressWarnings("rawtypes")
public class TypeUtilsTest{
	
	private Object param1;
	private Object param2;
	private Object param3;
	private Object param4;
	private testTypes type;
	
	enum testTypes{
		test_0,
		test_1,
		test_2,
		test_cast_Integer,
		test_cast_Integer_2,
		test_cast_to_long,
		test_cast_to_Long,
		test_cast_to_short,
		test_cast_to_Short,
		test_cast_to_byte,
		test_cast_to_Byte,
		test_cast_to_BigInteger,
		test_cast_to_BigDecimal,
		test_cast_to_boolean,
		test_cast_to_Boolean,
		test_cast_null,
		test_cast_to_String,
		test_cast_to_Date,
		test_cast_to_SqlDate,
		test_cast_to_SqlDate_string,
		test_cast_to_SqlDate_null,
		test_cast_to_SqlDate_null2,
		test_cast_to_SqlDate_util_Date,
		test_cast_to_SqlDate_sql_Date,
		test_cast_to_SqlDate_sql_Date2,
		test_cast_to_SqlDate_calendar,
		test_cast_to_SqlDate_error,
		test_cast_to_Timestamp,
		test_cast_to_Timestamp_string,
		test_cast_to_Timestamp_number,
		test_cast_to_Timestamp_null,
		test_cast_to_Timestamp_null2,
		test_cast_to_Timestamp_1970_01_01_00_00_00,
		test_cast_to_BigDecimal_same,
		test_cast_to_BigInteger_same,
		test_cast_Array,
		test_cast_to_Timestamp_util_Date,
		test_cast_to_Timestamp_sql_Date,
		test_cast_to_Timestamp_sql_Timestamp,
		test_cast_to_Timestamp_calendar,
		test_cast_to_Timestamp_not_error,
		test_cast_ab,
		test_cast_ab_1,
		test_cast_ab_error,
		test_error,
		test_error_2,
		test_3
	}
	
@Parameterized.Parameters
public static Collection<Object[]> data(){
	
	HashMap map;
	JSONObject obj;
	long millis;
	java.sql.Date date;
	BigDecimal value;
	BigInteger valueInt;
	Timestamp timestamp;
	B b;
	
	
	Object[][] testData = {
			{testTypes.test_0, map = new HashMap(), map, Map.class, null},
			{testTypes.test_1, obj = new JSONObject(), obj, Map.class, null},
			{testTypes.test_2, 1L, "panlei", User.class, null},
			{testTypes.test_cast_Integer, 1L, "id", 1, int.class},
			{testTypes.test_cast_Integer_2, 1L,"id", 1, Integer.class},
			{testTypes.test_cast_to_long, 1, "id", 1L, long.class},
			{testTypes.test_cast_to_Long, 1, "id", 1L, Long.class},
			{testTypes.test_cast_to_short, 1, "id", (short) 1, short.class},
			{testTypes.test_cast_to_Short, 1, "id", (short) 1, Short.class},
			{testTypes.test_cast_to_byte, 1, "id", (byte) 1, byte.class},
			{testTypes.test_cast_to_Byte, 1, "id", (byte) 1, Byte.class},
			{testTypes.test_cast_to_BigInteger, 1, "id", new BigInteger("1"), BigInteger.class},
			{testTypes.test_cast_to_BigDecimal, 1, "id", new BigDecimal("1"), BigDecimal.class},
			{testTypes.test_cast_to_boolean, 1, "id", Boolean.TRUE, boolean.class},
			{testTypes.test_cast_to_Boolean, 1, "id", Boolean.TRUE, Boolean.class},
			{testTypes.test_cast_null, null, "id", null, Boolean.class},
			{testTypes.test_cast_to_String, 1, "id", "1", String.class},
			{testTypes.test_cast_to_Date, millis=System.currentTimeMillis(), "date", new Date(millis), Date.class},
			{testTypes.test_cast_to_SqlDate, millis=System.currentTimeMillis(), "date", new java.sql.Date(millis), java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_string, Long.toString(millis=System.currentTimeMillis()), "date", new java.sql.Date(millis), java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_null, null, "date", null, java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_null2, null, null, null, null},
			{testTypes.test_cast_to_SqlDate_util_Date, new Date(millis=System.currentTimeMillis()), "date", new java.sql.Date(millis), java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_sql_Date, new java.sql.Date(millis=System.currentTimeMillis()), "date", new java.sql.Date(millis), java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_sql_Date2, date=new java.sql.Date(System.currentTimeMillis()), date, null, null},
			{testTypes.test_cast_to_SqlDate_calendar, millis=System.currentTimeMillis(), "date", new java.sql.Date(millis), java.sql.Date.class},
			{testTypes.test_cast_to_SqlDate_error, "date", 0, java.sql.Date.class, null}, 
			{testTypes.test_cast_to_Timestamp, millis=System.currentTimeMillis(), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_string, Long.toString(millis=System.currentTimeMillis()), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_number, new BigDecimal(Long.toString(millis=System.currentTimeMillis())), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_null, null, "date", null, java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_null2, null, null, null, null},
			{testTypes.test_cast_to_Timestamp_1970_01_01_00_00_00, "Asia/Shanghai", new Timestamp(0), "1970-01-01 08:00:00", null},
			{testTypes.test_cast_to_BigDecimal_same, value=new BigDecimal("123"), value, null, null},
			{testTypes.test_cast_to_BigInteger_same, valueInt=new BigInteger("123"), valueInt, null, null},
			{testTypes.test_cast_Array, Integer[].class, new ArrayList(), null, null},
			{testTypes.test_cast_to_Timestamp_util_Date, new Date(millis=System.currentTimeMillis()), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_sql_Date, new java.sql.Date(millis=System.currentTimeMillis()), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_sql_Timestamp, timestamp=new java.sql.Timestamp(System.currentTimeMillis()), timestamp, null, null},
			{testTypes.test_cast_to_Timestamp_calendar,millis=System.currentTimeMillis(), "date", new java.sql.Timestamp(millis), java.sql.Timestamp.class},
			{testTypes.test_cast_to_Timestamp_not_error,"date", -1, java.sql.Timestamp.class, new Timestamp(-1L)},
			{testTypes.test_cast_ab,b=new B(), "value", b, A.class},
			{testTypes.test_cast_ab_1,b=new B(), "value", b, IA.class},
			{testTypes.test_cast_ab_error, "value", new A(), B.class, null},
			{testTypes.test_error, "id", 1, C.class, null},
			{testTypes.test_error_2, "id", 1, "f", List.class},
			{testTypes.test_3, 1L, "panlei", User.class, null},			
	};
	
	return Arrays.asList(testData);
}

public TypeUtilsTest(testTypes type, Object param1, Object param2, Object param3, Object param4) throws NoSuchMethodException, SecurityException {
	this.type= type;
	
	if(type==testTypes.test_2 || type == testTypes.test_3) {
		configureTest2_3(param1, param2, param3);
	}
	if(type == testTypes.test_cast_Integer || type == testTypes.test_cast_Integer_2 || type == testTypes.test_cast_to_long
	|| type == testTypes.test_cast_to_Long || type == testTypes.test_cast_to_short || type == testTypes.test_cast_to_Short
	|| type == testTypes.test_cast_to_byte || type == testTypes.test_cast_to_Byte || type == testTypes.test_cast_to_BigInteger
	|| type == testTypes.test_cast_to_BigDecimal || type == testTypes.test_cast_to_boolean || type == testTypes.test_cast_to_Boolean
	|| type == testTypes.test_cast_null || type == testTypes.test_cast_to_String || type == testTypes.test_cast_to_Date
	|| type == testTypes.test_cast_to_SqlDate || type == testTypes.test_cast_to_SqlDate_string || type == testTypes.test_cast_to_SqlDate_null
	|| type == testTypes.test_cast_to_SqlDate_util_Date || type == testTypes.test_cast_to_SqlDate_sql_Date || type == testTypes.test_cast_to_Timestamp
	|| type == testTypes.test_cast_to_Timestamp_string || type == testTypes.test_cast_to_Timestamp_number || type == testTypes.test_cast_to_Timestamp_null
	|| type == testTypes.test_cast_to_Timestamp_util_Date || type == testTypes.test_cast_to_Timestamp_sql_Date || type == testTypes.test_cast_ab
	|| type == testTypes.test_cast_ab_1) {
		configureTestCastTypes(param1, param2, param3, param4);
	}
	if(type == testTypes.test_cast_to_SqlDate_calendar || type == testTypes.test_cast_to_Timestamp_calendar) {
		configureTestCastCalendar(param1, param2, param3, param4);
	}
	if(type == testTypes.test_cast_to_SqlDate_error || type == testTypes.test_cast_to_Timestamp_not_error || type == testTypes.test_cast_ab_error) {
		configureTestCastError(param1, param2, param3, param4);
	}
	if(type == testTypes.test_cast_to_Timestamp_1970_01_01_00_00_00) {
		configureTestTimezone(param1,param2,param3);
	}
	if(type == testTypes.test_error) {
		configureTestError(param1,param2,param3,param4);
	}
	if(type == testTypes.test_error_2) {
		configureTestError2(param1, param2, param3, param4);
	}
	if(type == testTypes.test_cast_Array){
		this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
	}
}


public void configureTest2_3(Object param1, Object param2, Object param3){
    JSONObject obj = new JSONObject();
    obj.put("id", 1);
    obj.put("name", param2);
    
    this.param1 = param1;
    this.param2 = obj;
    this.param3 = param2;
    this.param4 = param3;
}

public void configureTestCastTypes(Object param1, Object param2, Object param3, Object param4) {
	JSONObject obj = new JSONObject();
	obj.put((String) param2, param1);
	this.param1 = param3; 
    this.param2 = obj; 
    this.param3 = param2;
    this.param4 = param4;
}

public void configureTestCastCalendar(Object param1, Object param2, Object param3, Object param4) {
	JSONObject obj = new JSONObject();
	Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis((Long) param1);
    obj.put((String) param2, calendar);
    this.param1 = param3; 
    this.param2 = obj; 
    this.param3 = param2;
    this.param4 = param4;
}

public void configureTestCastError(Object param1, Object param2, Object param3, Object param4) {
    JSONObject obj = new JSONObject();
    obj.put((String) param1, param2);

    this.param1 = obj;
    this.param2 = param1;
    this.param3 = param3;
    this.param4 = param4;
}

public void configureTestTimezone(Object param1, Object param2, Object param3) {
    JSON.defaultTimeZone = TimeZone.getTimeZone((String) param1);
    this.param1 = param2;
    this.param2 = param3;
}

public void configureTestError(Object param1, Object param2, Object param3, Object param4) {
    JSONObject localParam = new JSONObject();
    localParam.put((String) param1, param2);
    this.param1 = localParam;
    this.param3 = ParserConfig.getGlobalInstance();
    this.param2 = param3; 
}

public void configureTestError2(Object param1, Object param2, Object param3, Object param4) throws NoSuchMethodException, SecurityException {
	JSONObject localParam = new JSONObject();
    localParam.put((String) param1, param2);
    this.param1 = localParam;
    this.param3 = ParserConfig.getGlobalInstance();
    Method method = TypeUtilsTest.class.getMethod((String) param3, (Class<?>) param4);
    this.param2 = method.getGenericParameterTypes()[0];
}



@Test
public void test_0() {
	Assume.assumeTrue(type.equals(testTypes.test_0));
	Assert.assertTrue(param1 == TypeUtils.castToJavaBean(param2,(Class<?>) param3));
}

@Test
public void test_1() {
	Assume.assumeTrue(type.equals(testTypes.test_1));
	Assert.assertTrue(param1 == TypeUtils.castToJavaBean(param2, (Class<?>) param3));
}

@Test
public void test_2() {
	Assume.assumeTrue(type.equals(testTypes.test_2));
    User user = (User) TypeUtils.castToJavaBean(param2, (Class<?>) param4);
    Assert.assertEquals(param1, user.getId());
    Assert.assertEquals(param3, user.getName());
  
}

@Test
public void test_cast_Integer() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_Integer));
	JSONObject json = (JSONObject) param2;
    Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_Integer_2() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_Integer_2));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_long() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_long));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Long() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Long));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_short() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_short));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Short() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Short));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_byte() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_byte));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Byte() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Byte));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_BigInteger() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_BigInteger));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_BigDecimal() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_BigDecimal));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_boolean() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_boolean));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Boolean() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Boolean));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_null() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_null));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_String() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_String));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Date() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Date));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_string() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_string));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_null() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_null));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_null2() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_null2));
    Assert.assertEquals(param1, TypeUtils.castToSqlDate(param2));
}

@Test
public void test_cast_to_SqlDate_util_Date() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_util_Date));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_sql_Date() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_sql_Date));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_sql_Date2() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_sql_Date2));
    Assert.assertEquals(param1, TypeUtils.castToSqlDate(param2));
}

@Test
public void test_cast_to_SqlDate_calendar() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_calendar));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_SqlDate_error() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_SqlDate_error));
    JSONObject json = (JSONObject) param1;
    JSONException error = null;
    try {
        json.getObject((String) param2, (Class<?>) param3);
    } catch (JSONException e) {
        error = e;
    }
    Assert.assertNotNull(error);
}

@Test
public void test_cast_to_Timestamp() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_string() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_string));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_number() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_number));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_null() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_null));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_null2() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_null2));
	Assert.assertEquals(param1, TypeUtils.castToTimestamp(param2));
}

@Test
public void test_cast_to_Timestamp_1970_01_01_00_00_00() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_1970_01_01_00_00_00));
	Assert.assertEquals(param1, TypeUtils.castToTimestamp(param2));
}

@Test
public void test_cast_to_BigDecimal_same() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_BigDecimal_same));
    Assert.assertEquals(param1, TypeUtils.castToBigDecimal(param2));
}

@Test
public void test_cast_to_BigInteger_same() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_BigInteger_same));
    Assert.assertEquals(param1, TypeUtils.castToBigInteger(param2));
}

@Test
public void test_cast_Array() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_Array));
    Assert.assertEquals(param1, TypeUtils.cast(param2, (Class<?>) param1, (ParserConfig) param3).getClass());
}

@Test
public void test_cast_to_Timestamp_util_Date() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_util_Date));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_sql_Date() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_sql_Date));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_sql_Timestamp() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_sql_Timestamp));
    Assert.assertEquals(param1, TypeUtils.castToTimestamp(param2));
}

@Test
public void test_cast_to_Timestamp_calendar() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_calendar));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_to_Timestamp_not_error() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_to_Timestamp_not_error));

    JSONObject json = (JSONObject) param1;
    JSONException error = null;
    try {
        json.getObject((String) param2, (Class<?>)param3);
    } catch (JSONException e) {
        error = e;
    }
    Assert.assertNull(error);
    Assert.assertEquals(param4, json.getObject((String) param2, (Class<?>)param3));
}

@Test
public void test_cast_ab() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_ab));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_ab_1() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_ab_1));
	JSONObject json = (JSONObject) param2;
	Assert.assertEquals(param1, json.getObject((String) param3, (Class<?>) param4));
}

@Test
public void test_cast_ab_error() {
	Assume.assumeTrue(type.equals(testTypes.test_cast_ab_error));
    JSONObject json = (JSONObject) param1;
    JSONException error = null;
    try {
        json.getObject((String) param2, (Class<?>) param3);
    } catch (JSONException e) {
        error = e;
    }
    Assert.assertNotNull(error);
}

@Test
public void test_error() {
	Assume.assumeTrue(type.equals(testTypes.test_error));
    JSONException error = null;
    try {
        TypeUtils.castToJavaBean((JSONObject) param1, (Class<?>) param2, (ParserConfig) param3);
    } catch (JSONException e) {
        error = e;
    }
    Assert.assertNotNull(error);
}

@Test
public void test_error_2() {
	Assume.assumeTrue(type.equals(testTypes.test_error_2));
    Throwable error = null;
    try {
        TypeUtils.cast(param1, (java.lang.reflect.Type) param2, (ParserConfig) param3);
    } catch (JSONException e) {
        error = e;
    }
    Assert.assertNotNull(error);
}

@Test
public void test_3() {
	Assume.assumeTrue(type.equals(testTypes.test_3));
    User user = (User) JSON.toJavaObject((JSON) param2, (Class<?>) param4);
    Assert.assertEquals(param1, user.getId());
    Assert.assertEquals(param3, user.getName());
}


    public static class User {

        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class A implements IA {

    }

    public static interface IA {

    }

    public static class B extends A {

    }

    public static class C extends B {

        public int getId() {
            throw new UnsupportedOperationException();
        }

        public void setId(int id) {
            throw new UnsupportedOperationException();
        }
    }

    public static void f(List<?> list) {

    }
}