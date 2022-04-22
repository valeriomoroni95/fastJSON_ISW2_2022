package fastJSON_ISW2;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert;

import com.alibaba.fastjson.serializer.SerializeWriter;

@RunWith(Parameterized.class)
public class SerializeWriterTest{
	
	private char param1;
	private int param2;
	private String param3;
	private long param4;
	private String param5;
	private int param6;
	private String param7;
	private char param8;
	private long param9;
	private String param10;
	
	/* Array of tests */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {'A', 156, "A156", 345, "A156345", -1, "-1", ',',-1L,"-1,"}
        });
    }
    
    public SerializeWriterTest(char param1, int param2, String param3, long param4, String param5, int param6, String param7, char param8, long param9, String param10) {
    	configure(param1,param2,param3,param4,param5,param6,param7,param8,param9,param10);
    }
    
    public void configure(char param1, int param2, String param3, long param4, String param5, int param6, String param7, char param8, long param9, String param10) {
    	this.param1=param1;
    	this.param2=param2;
    	this.param3=param3;
    	this.param4=param4;
    	this.param5=param5;
    	this.param6=param6;
    	this.param7=param7;
    	this.param8=param8;
    	this.param9=param9;
    	this.param10=param10;
    	
    }
    
    public SerializeWriter configureWriter() {
    	SerializeWriter writer = new SerializeWriter();
    	return writer;
    }
    
    @Test
    public void test_0() {
    	SerializeWriter writer = configureWriter();
    	writer.append(param1);
    	writer.writeInt(param2);
    	System.out.println(param3+ " and the other one is "+ writer.toString());
    	Assert.assertEquals(param3, writer.toString());
    	writer.writeLong(param4);
    	System.out.println(param5+ " and the other one is "+ writer.toString());
    	Assert.assertEquals(param5, writer.toString());
    	
    }
    
    @Test
    public void test_1() {
    	SerializeWriter writer = configureWriter();
		writer.writeInt(param6);
		System.out.println(param7+ " and the other one is "+ writer.toString());
		Assert.assertEquals(param7, writer.toString());
    }
    
    @Test
    public void test_4() {
    	SerializeWriter writer = configureWriter();
		writer.writeInt(param6);
		writer.write(param8);
		System.out.println(param10+ " and the other one is "+ writer.toString());
		Assert.assertEquals(param10, writer.toString());
    }
    
    @Test
    public void test_5() {
    	SerializeWriter writer = configureWriter();
		writer.writeLong(param9);
		System.out.println(param7+ " and the other one is "+ writer.toString());
		Assert.assertEquals(param7, writer.toString());
    }
    
    @Test
    public void test_6() {
    	SerializeWriter writer = configureWriter();
		writer.writeLong(param9);
		writer.write(param8);
		System.out.println(param10+ " and the other one is "+ writer.toString());
		Assert.assertEquals(param10, writer.toString());
    }
	
}
