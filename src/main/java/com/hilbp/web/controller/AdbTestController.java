package com.hilbp.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilbp.adb.action.ActionSchedule;
import com.hilbp.adb.client.TencentAiService;
import com.hilbp.adb.entity.Node;
import com.hilbp.adb.util.AdbShellUtil;

import cn.xsshome.taip.nlp.TAipNlp;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

@RestController
@RequestMapping("/adb")
public class AdbTestController {
	
	@Autowired
	AdbShellUtil adbShellUtil;
	
	@Autowired
	TAipNlp tAipNlp;
	

	@Autowired
	@Qualifier("reflectActionSchedule")
	ActionSchedule reflectActionSchedule;

	@Autowired
    public TencentAiService feignClientService;
	
	
	

	@RequestMapping("/api1")
	public ResponseEntity<Object> api1() throws Exception {
		
//		TAipNlp aipNlp = new TAipNlp("2119891201", "QVsTZBOwEUl6vFhN");
        String session = new Date().getTime()/1000+"";
//        String result = aipNlp.nlpWordseg("小帅开发者");//分词
//        String result = aipNlp.nlpWordpos("小帅是一个热心的开发者");//词性标注
//        String result = aipNlp.nlpWordner("最近张学友在深圳开了一场演唱会");//专有名词
//        String result = aipNlp.nlpWordsyn("今天的天气怎么样");//同义词
//        String result = aipNlp.nlpWordcom("今天深圳的天气怎么样？明天呢");//意图成分
//        String result = aipNlp.nlpTextpolar("小帅很帅");//情感分析
        String result = tAipNlp.nlpTextchat(session,"笑话");//基础闲聊     
        
//        String result = aipNlp.nlpTextTrans(0, "小帅开发者");//文本翻译（AI Lab）
//        String result = aipNlp.nlpTextTranslate("小帅开发者", "zh", "en");//文本翻译（翻译君）     
//        String result = aipNlp.nlpImageTranslate(filePath, session, "doc","zh", "en");//图片翻译
//        String result = aipNlp.nlpSpeechTranslate(6, 0, 1, session, filePath2,"zh", "en");//语音翻译     
//        String result = aipNlp.nlpTextDetect("こんにちは", 0);//语种识别
        System.out.println(result);
	
		return null;
	}


//	//获取服务状态
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<Object> test(String service_name) throws IOException, JadbException{
		
		JadbDevice device = adbShellUtil.getDevices().get(0);
		
		String activity = adbShellUtil.getFocusedActivity(device);
		
		return new ResponseEntity<Object>(activity, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public ResponseEntity<Object> input() throws IOException, JadbException{

		return new ResponseEntity<Object>(this.getTargetNode(), HttpStatus.OK);
	}
	
	
	public List<Node> getTargetNode() {
		
		List<Node> nodes = new ArrayList<Node>();
		SAXReader reader = new SAXReader();
		
		try {
			String xpath = "//node[@text='我' and @resource-id='com.ss.android.ugc.aweme:id/f8m']/..";
			String uiSavePath = "E:/adb/u10.xml";
			
			Document document = reader.read(uiSavePath);
			@SuppressWarnings("unchecked")
			List<Element> list = document.selectNodes(xpath);
			for(Element item : list) {
				Attribute bounds = item.attribute("bounds");
				Attribute text = item.attribute("text");
				String str = text.getStringValue();
				
				Node node = new Node();
				node.setText(str);
				node.setBounds(bounds.getStringValue());
			
				nodes.add(node);
			}
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		return nodes;
	}

}
