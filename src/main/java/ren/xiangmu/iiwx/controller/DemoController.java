package ren.xiangmu.iiwx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	private String show(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "demo"; /** 返回的是显示数据的html的文件名 */
	}
	
	@RequestMapping(value = "/demo1", method = RequestMethod.GET)
	private String demo(ModelMap map) { /** @RequestParam("name")绑定请求地址中的name到参数name中 ModelMap map 存放返回内容 */
		return "test/demo1"; /** 返回的是显示数据的html的文件名 */
	}
}
