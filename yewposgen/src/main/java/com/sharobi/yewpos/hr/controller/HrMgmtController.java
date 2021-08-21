package com.sharobi.yewpos.hr.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sharobi.yewpos.hr.model.Department;
import com.sharobi.yewpos.hr.model.Designation;
import com.sharobi.yewpos.hr.model.DutyShift;
import com.sharobi.yewpos.hr.model.EmpAttendance;
import com.sharobi.yewpos.hr.model.EmpShiftSchedule;
import com.sharobi.yewpos.hr.model.EmpShiftScheduleList;
import com.sharobi.yewpos.hr.model.Employee;
import com.sharobi.yewpos.hr.model.EmployeeFile;
import com.sharobi.yewpos.hr.model.EmployeeLeave;
import com.sharobi.yewpos.hr.model.EmployeeType;
import com.sharobi.yewpos.hr.service.HrMgmtService;
import com.sharobi.yewpos.login.model.LoginInfoByUserDTO;
import com.sharobi.yewpos.role.model.MenuByUserDTO;
import com.sharobi.yewpos.role.model.MenuSelection;
import com.sharobi.yewpos.role.service.RoleService;
import com.sharobi.yewpos.util.Constant;
import com.sharobi.yewpos.util.ForwardConstants;
import com.sharobi.yewpos.util.RoleBasedCostants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

@Controller
@RequestMapping("/hr")
public class HrMgmtController {

	private final static Logger logger = LoggerFactory.getLogger(HrMgmtController.class);

	@Autowired
	HrMgmtService hrMgmtService;

	@Autowired
	RoleService roleService;

	private final static Gson gson = new Gson();

	@RequestMapping(value = "/loaddept", method = RequestMethod.GET)
	public ModelAndView viewmdepartment(Model model, HttpSession session) {
		//logger.debug("In loaddept......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		}
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
				(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
				userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
		MenuSelection menuselect = new MenuSelection();
		menuselect.setMenu(Constant.HR);
		menuselect.setSubMenu(Constant.HR_SETUP);
		menuselect.setChildsubMenu(Constant.HR_DEPT);
		mav.addObject("menuselect", menuselect);
		mav.addObject("menuByUserDTO", menuByUserDTO);
		List<Department> deptList = hrMgmtService.getAllDepartmentsByStoreId(userInfo.getStoreId());
		if (deptList == null || deptList.isEmpty() == true || deptList.size() <= 0) {
			mav.addObject("departmentList", null);
		} else {
			mav.addObject("departmentList", deptList);
		}
		// mav.addObject("departmentList", deptList);
		mav.setViewName(ForwardConstants.VIEW_HR_DEPT);
		return mav;
	}

	// For Department Function

	@RequestMapping(value = "/adddepartment", method = RequestMethod.POST)
	public void addDepartment(@RequestBody String departmentCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In addmenucategory......{}", departmentCategoryString);
		// ModelAndView mav = new ModelAndView();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			Gson gson = new GsonBuilder().create();
			Department dept = gson.fromJson(departmentCategoryString, new TypeToken<Department>() {
			}.getType());

			// category.setStoreId(userInfo.getStoreId());
			dept.setCreatedDate(today);
			dept.setCreatedBy(String.valueOf(userInfo.getId()));
			String res = hrMgmtService.addDepartment(dept);
			//System.out.println("res:" + res);
		     out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/getDutyShiftsById", method = RequestMethod.GET)
	@ResponseBody
	public String getDutyShiftsById(@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(value = "id") Integer id, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In getDutyShiftsById......");
		// System.out.println("GET DUTY SHIFT BY ID");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			// PrintWriter out = response.getWriter();
			// response.setContentType("text/plain");
			DutyShift dutyshift = hrMgmtService.getDutyShiftsById(storeId, id);
			Gson gson = new Gson();
			String gresponse = gson.toJson(dutyshift);
			// System.out.println("res:" + gresponse);
			// out.print(gresponse);
			// out.flush();
			return gresponse;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/editdepartment", method = RequestMethod.POST)
	public void editDepartment(@RequestBody String departmentCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In designationmenucategory......{}", departmentCategoryString);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			Department dept = gson.fromJson(departmentCategoryString, new TypeToken<Department>() {
			}.getType());
			dept.setUpdatedBy(String.valueOf(userInfo.getId()));
			dept.setUpdatedDate(today);
			String res = hrMgmtService.editDepartment(dept);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deletedepartment", method = RequestMethod.POST)
	public void deleteDepartment(@RequestBody String departmentCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In deletedepartment......{}", departmentCategoryString);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			Department category = gson.fromJson(departmentCategoryString, new TypeToken<Department>() {
			}.getType());

			String res = hrMgmtService.deleteDepartment(category);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loaddesig", method = RequestMethod.GET)
	public ModelAndView viewmdesignation(Model model, HttpSession session) {
		//logger.debug("In loaddesig......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {

			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_SETUP);
			menuselect.setChildsubMenu(Constant.HR_DESIG);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<Designation> desgList = hrMgmtService.getAllDesignationByStoreId(userInfo.getStoreId());
			session.setAttribute("designationList", desgList);
			// mav.addObject(Constants.ADMIN, "Y");

			mav.setViewName(ForwardConstants.VIEW_HR_DESIGNATION);

		}

		return mav;
	}

	@RequestMapping(value = "/adddesignation", method = RequestMethod.POST)
	public void addDesignation(@RequestBody String designationCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In addmenucategory......{}", designationCategoryString);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		String lang = null;

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			Gson gson = new GsonBuilder().create();
			Designation designation = gson.fromJson(designationCategoryString, new TypeToken<Designation>() {
			}.getType());

			designation.setStoreId(userInfo.getStoreId());
			designation.setCreatedDate(today);
			designation.setCreatedBy(String.valueOf(userInfo.getId()));
			String res = hrMgmtService.addDesignation(designation);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	// getDutyShiftsById
	@RequestMapping(value = "/editdesignation", method = RequestMethod.POST)
	public void editDesignation(@RequestBody String designationCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In designationmenucategory......{}", designationCategoryString);
		// ModelAndView mav = new ModelAndView();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			Designation category = gson.fromJson(designationCategoryString, new TypeToken<Designation>() {
			}.getType());
			category.setUpdatedBy(String.valueOf(userInfo.getId()));
			category.setUpdatedDate(today);
			String res = hrMgmtService.editDesignation(category);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deletedesignation", method = RequestMethod.POST)
	public void deleteDesignation(@RequestBody String designationCategoryString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In deletedesignation......{}", designationCategoryString);
		// ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			Designation designation = gson.fromJson(designationCategoryString, new TypeToken<Designation>() {
			}.getType());

			String res = hrMgmtService.deleteDesignation(designation);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loaddutyshift", method = RequestMethod.GET)
	public ModelAndView loaddutyshift(Model model, HttpSession session) {
		//logger.debug("In loaddutyshift......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {

			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_SETUP);
			menuselect.setChildsubMenu(Constant.HR_DUTYSHIFT);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<DutyShift> dutyList = hrMgmtService.getAllDutyShiftByStoreId(userInfo.getStoreId());

			session.setAttribute("dutyShiftList", dutyList);
			mav.addObject(Constant.ADMIN, "Y");

			mav.setViewName(ForwardConstants.VIEW_HR_DUTYSHIFT);

		}

		// mav.setViewName("hr/dutyshift");
		return mav;
	}

	@RequestMapping(value = "/adddutyshift", method = RequestMethod.POST)
	public void addDutyshift(@RequestBody String dutyShiftString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In adddutyshift......{}", dutyShiftString);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			Gson gson = new GsonBuilder().create();
			DutyShift dutyshift = gson.fromJson(dutyShiftString, new TypeToken<DutyShift>() {
			}.getType());

			dutyshift.setStoreId(userInfo.getStoreId());
			dutyshift.setCreatedDate(today);
			dutyshift.setCreatedBy(String.valueOf(userInfo.getId()));
			String res = hrMgmtService.addDutyshift(dutyshift);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/editdutyshift", method = RequestMethod.POST)
	public void editDutyshift(@RequestBody String dutyShiftString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In editdutyshift......{}", dutyShiftString);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			DutyShift dutyshift = gson.fromJson(dutyShiftString, new TypeToken<DutyShift>() {
			}.getType());
			dutyshift.setUpdatedBy(String.valueOf(userInfo.getId()));
			dutyshift.setUpdatedDate(today);
			String res = hrMgmtService.editDutyshift(dutyshift);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deletedutyshift", method = RequestMethod.POST)
	public void deleteDutyshift(@RequestBody String dutyShiftString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In deletedutyshift......{}", dutyShiftString);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			DutyShift dutyshift = gson.fromJson(dutyShiftString, new TypeToken<DutyShift>() {
			}.getType());

			String res = hrMgmtService.deleteDutyshift(dutyshift);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/addemployeetype", method = RequestMethod.POST)
	public void addEmployeetype(@RequestBody String employeeTypeString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In addemployeetype......{}", employeeTypeString);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");

			Gson gson = new GsonBuilder().create();
			EmployeeType emptype = gson.fromJson(employeeTypeString, new TypeToken<EmployeeType>() {
			}.getType());

			emptype.setStoreId(userInfo.getStoreId());
			emptype.setCreatedDate(today);
			emptype.setCreatedBy(String.valueOf(userInfo.getId()));
			String res = hrMgmtService.addEmployeetype(emptype);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String deleteEmployee(@RequestBody String employeeString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In deleteEmployee......{}", employeeString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_delete", false);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			Gson gson = new GsonBuilder().create();
			Employee employee = gson.fromJson(employeeString, new TypeToken<Employee>() {
			}.getType());

			String res = hrMgmtService.deleteEmployee(employee);
			if (res.equalsIgnoreCase("Success")) {
				jobj.addProperty("is_delete", true);
			} else {
				jobj.addProperty("is_delete", false);
			}

		}
		return jobj.toString();
	}

	@RequestMapping(value = "/getEmployeeTypesById", method = RequestMethod.GET)
	@ResponseBody
	public String getEmployeeTypesById(@RequestParam(value = "storeId") Integer storeId,
			@RequestParam(value = "id") Integer id, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In getEmployeeTypesById......");

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			EmployeeType employeetype = hrMgmtService.getEmployeeTypesById(storeId, id);
			Gson gson = new Gson();
			String gresponse = gson.toJson(employeetype);
			//System.out.println("res:" + gresponse);

			return gresponse;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/editemployeetype", method = RequestMethod.POST)
	public void editEmployeetype(@RequestBody String employeeTypeString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In designationmenucategory......{}", employeeTypeString);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			EmployeeType emptype = gson.fromJson(employeeTypeString, new TypeToken<EmployeeType>() {
			}.getType());
			emptype.setUpdatedBy(String.valueOf(userInfo.getId()));
			emptype.setUpdatedDate(today);
			String res = hrMgmtService.editEmployeetype(emptype);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/deleteemployeetype", method = RequestMethod.POST)
	public void deleteEmployeetype(@RequestBody String employeetypeString, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		//logger.debug("In deleteEmployeetype......{}", employeetypeString);

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			EmployeeType emptype = gson.fromJson(employeetypeString, new TypeToken<EmployeeType>() {
			}.getType());

			String res = hrMgmtService.deleteEmployeetype(emptype);
			//System.out.println("res:" + res);
			out.print(res);
			out.flush();
		}
	}

	@RequestMapping(value = "/loadshiftschedule", method = RequestMethod.GET)
	public ModelAndView loadshiftschedule(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {
			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_SHIFTSCHEDULE);
			// menuselect.setChildsubMenu(Constant.HR_DUTYSHIFT);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<DutyShift> dutyList = hrMgmtService.getAllDutyShiftByStoreId(userInfo.getStoreId());
			session.setAttribute("dutyShiftList", dutyList);
			mav.setViewName(ForwardConstants.VIEW_HR_SHIFT_SCHEDULE);
		}

		return mav;
	}

	@RequestMapping(value = "/getAllEmpShiftSchedule", method = RequestMethod.GET)
	@ResponseBody
	public String getAllEmpShiftSchedule(@RequestHeader("storeId") String storeId,
			@RequestHeader("fromDate") String fromDate, @RequestHeader("toDate") String toDate, HttpSession session,
			HttpServletRequest request) throws Exception {

		List<EmpShiftSchedule> empshiftschedulelist = null;
		String response = null;
		//logger.debug("In getAllEmpShiftSchedule......");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			empshiftschedulelist = hrMgmtService.getAllEmpShiftSchedule(storeId, fromDate, toDate);

			if (empshiftschedulelist != null) {
				Gson gson = new Gson();
				String gresponse = gson.toJson(empshiftschedulelist);
				response = gresponse;
			}
		}
		return response;
	}

	@RequestMapping(value = "/getEmpShiftScheduleByEmpIdandDate", method = RequestMethod.GET)
	@ResponseBody
	public String getEmpShiftScheduleByStoreIdAndEmpIdAndDate(@RequestHeader("storeId") String storeId,
			@RequestHeader("empId") String empId, @RequestParam("date") String date, HttpSession session,
			HttpServletRequest request) throws Exception {

		List<EmpShiftSchedule> empshiftschedulelist = null;
		String response = null;
		//logger.debug("In getEmpShiftScheduleByStoreIdAndEmpIdAndDate......");

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			empshiftschedulelist = hrMgmtService.getEmpShiftScheduleByStoreIdAndEmpIdAndDate(storeId, empId, date);

			if (empshiftschedulelist != null) {
				Gson gson = new Gson();
				String gresponse = gson.toJson(empshiftschedulelist);
				response = gresponse;
			}
		}
		return response;
	}

	@RequestMapping(value = "/addEmpShiftSchedule", method = RequestMethod.POST)
	@ResponseBody
	public String addEmpShiftSchedule(@RequestBody String employeeShiftScheduleString, HttpSession session)
			throws IOException {
		//logger.debug("In addEmpShiftSchedule......{}", employeeShiftScheduleString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_success", false);
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			Gson gson = new GsonBuilder().create();
			EmpShiftScheduleList empShiftScheduleList = gson.fromJson(employeeShiftScheduleString,
					new TypeToken<EmpShiftScheduleList>() {
					}.getType());
			String res = hrMgmtService.addEmployeeShiftSchedule(empShiftScheduleList);
			if (res.equalsIgnoreCase("Success")) {
				jobj.addProperty("is_success", true);
			} else {
				jobj.addProperty("is_success", false);
			}

		}
		return jobj.toString();
	}

	@RequestMapping(value = "/updateEmpShiftSchedule", method = RequestMethod.POST)
	@ResponseBody
	public String updateEmpShiftSchedule(@RequestBody String employeeShiftScheduleString, HttpSession session)
			throws IOException {
		//logger.debug("In updateEmpShiftSchedule......{}", employeeShiftScheduleString);
		//logger.debug(employeeShiftScheduleString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_success", false);
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			Gson gson = new GsonBuilder().create();
			EmpShiftScheduleList empShiftScheduleList = gson.fromJson(employeeShiftScheduleString,
					new TypeToken<EmpShiftScheduleList>() {
					}.getType());
			String res = hrMgmtService.updateEmployeeShiftSchedule(empShiftScheduleList);
			if (res.equalsIgnoreCase("Success")) {
				jobj.addProperty("is_success", true);
			} else {
				jobj.addProperty("is_success", false);
			}
		}
		return jobj.toString();
	}

	@RequestMapping(value = "/cancelEmpShiftSchedule", method = RequestMethod.POST)
	@ResponseBody
	public String cancelEmpShiftSchedule(@RequestBody String employeeShiftScheduleString, HttpSession session)
			throws IOException {
		//logger.debug("In cancelEmpShiftSchedule......{}", employeeShiftScheduleString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_success", false);
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			Gson gson = new GsonBuilder().create();
			EmpShiftScheduleList empShiftScheduleList = gson.fromJson(employeeShiftScheduleString,
					new TypeToken<EmpShiftScheduleList>() {
					}.getType());
			String res = hrMgmtService.cancelEmployeeShiftSchedule(empShiftScheduleList);
			if (res.equalsIgnoreCase("Success")) {
				jobj.addProperty("is_success", true);
			} else {
				jobj.addProperty("is_success", false);
			}
		}
		return jobj.toString();

	}

	@RequestMapping(value = "/addEmpAttendance", method = RequestMethod.POST)
	@ResponseBody
	public String addEmpAttendance(@RequestBody String employeeAttendanceString, HttpSession session)
			throws IOException {
		//logger.debug("In addEmpAttendance......{}", employeeAttendanceString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_success", false);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			Gson gson = new GsonBuilder().create();
			EmpAttendance empattendance = gson.fromJson(employeeAttendanceString, new TypeToken<EmpAttendance>() {
			}.getType());
			empattendance.setCreatedDate(today);
			empattendance.setCreatedBy(String.valueOf(userInfo.getId()));
			String res = hrMgmtService.addEmployeeAttendance(empattendance);

			if (res.equalsIgnoreCase("Success")) {
				jobj.addProperty("is_success", true);
			} else {
				jobj.addProperty("is_success", false);
			}

		}
		return jobj.toString();
	}

	@RequestMapping(value = "/getAllEmpAttendance", method = RequestMethod.GET)
	@ResponseBody
	public String getAllEmpAttendance(@RequestHeader("storeId") String storeId,
			@RequestHeader("fromDate") String fromDate, @RequestHeader("toDate") String toDate, HttpSession session,
			HttpServletRequest request) throws Exception {

		List<EmpAttendance> empattendancelist = null;
		String response = null;
		//logger.debug("In getAllEmpAttendance......");

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			response = hrMgmtService.getAllEmployeeAttendance(storeId, fromDate, toDate);
		}
		//logger.debug("RESPONSE IN HRMANAGEMENNT CONTROLLER FOR GET ALL EMPLOYEE ATTENDANCE LIST BY FROMDATE AND TODATE AND STOREID IS......");
		//logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/getEmpAttendanceByEmpIdandDate", method = RequestMethod.GET)
	@ResponseBody
	public String getEmpAttendanceByStoreIdEmpIdandDate(@RequestHeader("storeId") String storeId,
			@RequestHeader("empId") String empId, @RequestParam("date") String date, HttpSession session,
			HttpServletRequest request) throws Exception {
		String response = null;
		//logger.debug("In getEmpAttendanceByEmpIdandDate......");

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			response = hrMgmtService.getEmployeeAttendanceByStoreIdAndEmpIdAndDate(storeId, empId, date);
		}
		//logger.debug("RESPONSE IN HRMANAGEMENNT CONTROLLER FOR GET ALL EMPLOYEE ATTENDANCE LIST BY STOREID AND EMPID AND DATE IS......");
		//logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/getEmpCalculationByYear", method = RequestMethod.GET)
	@ResponseBody
	public String getEmpCalculationByYear(@RequestHeader("storeId") String storeId,
			@RequestHeader("empId") String empId, @RequestHeader("fromDate") String fromDate,
			@RequestHeader("toDate") String toDate, HttpSession session, HttpServletRequest request) throws Exception {
		String response = null;
		//logger.debug("In getEmpAttendanceByEmpIdandDate......");

		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			response = hrMgmtService.getEmployeeLeaveCalculationByStoreIdAndEmpIdAndFromDateAndToDate(storeId, empId,
					fromDate, toDate);
		}
		//logger.debug("RESPONSE IN HRMANAGEMENNT CONTROLLER FOR GET ALL EMPLOYEE ATTENDANCE LIST BY STOREID AND EMPID AND DATE IS......");
		//logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/loademployeeleavedetails", method = RequestMethod.GET)
	public ModelAndView loademployeeleavedetail(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {
			mav.setViewName(ForwardConstants.VIEW_HR_EMPLOYEE_LEAVE_DETAILS);

		}
		return mav;
	}

	@RequestMapping(value = "/loadattendance", method = RequestMethod.GET)
	public ModelAndView loadattendance(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {
			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_ATTENDANCE);
			// menuselect.setChildsubMenu(Constant.HR_DUTYSHIFT);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<DutyShift> dutyList = hrMgmtService.getAllDutyShiftByStoreId(userInfo.getStoreId());
			session.setAttribute("dutyShiftList", dutyList);
			mav.setViewName(ForwardConstants.VIEW_HR_EMPLOYEE_ATTENDANCE);
			return mav;
		}

	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String addEmployee(@RequestBody String empString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException, ParseException {
		//logger.debug("In addEmployee......{}", empString);
		Employee employee = new Employee();
		Department deptobj = new Department();
		EmployeeType emptypeobj = new EmployeeType();
		Designation desigobj = new Designation();

		Date leavingDate = null;
		Date joiningDate = null;
		Integer empid = 0;
		JsonObject jobj = new JsonObject();
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {

			JsonObject jsonObject = new JsonParser().parse(empString).getAsJsonObject();
			Gson gson = new GsonBuilder().create();
			// System.out.println("RECEIVED JSON OBJECT
			// IS:"+jsonObject.toString());
			// System.out.println(jsonObject);

			JsonElement jsondeptElement = jsonObject.get("dept");
			String departmentnumber = jsondeptElement.getAsString();
			JsonElement jsondesignationElement = jsonObject.get("designation");
			String designo = jsondesignationElement.getAsString();

			JsonElement jsonemployeeTypeElement = jsonObject.get("employeeType");
			String employeeType = jsonemployeeTypeElement.getAsString();

			JsonElement jsonElement = jsonObject.get("emailId");
			String email = jsonElement.getAsString();

			JsonElement jsonpermanentAddressElement = jsonObject.get("permanentAddress");
			String permanentAddress = jsonpermanentAddressElement.getAsString();

			JsonElement jsonpresentAddressElement = jsonObject.get("presentAddress");
			String presentAddress = jsonpresentAddressElement.getAsString();

			JsonElement jsonmobileNoElement = jsonObject.get("mobileNo");
			String phoneNo = jsonmobileNoElement.getAsString();

			JsonElement jsoncodeElement = jsonObject.get("code");
			String code = jsoncodeElement.getAsString();

			JsonElement jsonnameElement = jsonObject.get("name");
			String name = jsonnameElement.getAsString();

			JsonElement jsoncasualLeaveElement = jsonObject.get("casualLeave");
			String casualLeave1 = jsoncasualLeaveElement.getAsString();

			JsonElement jsonsickLeaveElement = jsonObject.get("sickLeave");
			String sickLeave1 = jsonsickLeaveElement.getAsString();

			JsonElement jsonmiscLeaveElement = jsonObject.get("miscLeave");
			String miscLeave1 = jsonmiscLeaveElement.getAsString();

			JsonElement jsonearnedLeaveElement = jsonObject.get("earnedLeave");
			String earnedLeave1 = jsonearnedLeaveElement.getAsString();

			JsonElement jsonjoiningDateElement = jsonObject.get("joiningDate");
			String joiningDate1 = jsonjoiningDateElement.getAsString();

			JsonElement jsonleavingDateElement = jsonObject.get("leavingDate");
			String leavingDate1 = jsonleavingDateElement.getAsString();

			// System.out.println("RECEIVED EMAIL FROM JSON IS:"+email);

			// System.out.println("RECEIVED DepartmentNumber FROM JSON
			// IS:"+departmentnumber);
			deptobj.setId(Integer.parseInt(departmentnumber));
			emptypeobj.setId(Integer.parseInt(employeeType));
			desigobj.setId(Integer.parseInt(designo));
			Double casualLeave = Double.parseDouble(casualLeave1);
			Double sickLeave = Double.parseDouble(sickLeave1);
			Double miscLeave = Double.parseDouble(miscLeave1);
			Double earnedLeave = Double.parseDouble(earnedLeave1);

			// System.out.println("RECEIVED joiningDate FROM JSON
			// IS:"+joiningDate);

			// System.out.println("RECEIVED leavingDate FROM JSON
			// IS:"+leavingDate);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date todayDate1 = new Date();
			String todayformatteddate = formatter.format(todayDate1);
			employee.setName(name);
			employee.setEmailId(email);
			employee.setPresentAddress(presentAddress);
			employee.setPermanentAddress(permanentAddress);
			employee.setMobileNo(phoneNo);
			employee.setCode(code);
			employee.setStoreId(userInfo.getStoreId());
			employee.setCreatedBy(String.valueOf(userInfo.getId()));
			employee.setCreatedDate(todayformatteddate);
			employee.setEmployeeType(emptypeobj);
			employee.setDept(deptobj);
			employee.setDesignation(desigobj);
			if (joiningDate1.isEmpty() == false) {
				employee.setJoiningDate(joiningDate1);
			}
			if (leavingDate1.isEmpty() == false) {
				employee.setLeavingDate(leavingDate1);
			}
			employee.setCasualLeave(casualLeave);
			employee.setSickLeave(sickLeave);
			employee.setMiscLeave(miscLeave);
			employee.setEarnedLeave(earnedLeave);

			String res = hrMgmtService.addEmployee(employee);
			empid = Integer.parseInt(res);
			//System.out.println("EMPLOYEE ID AFTER SAVING IN DB IS:" + empid);
			// System.out.println("RESPONSE AFTER SAVING EMPLOYEE IN DB
			// IS:"+res);

		}
		jobj.addProperty("empid", empid);
		return jobj.toString();

	}

	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	@ResponseBody
	public String getAllEmployeesByStoreId(@RequestHeader("storeId") Integer storeId, HttpSession session) {
		List<Employee> employeeslist = null;
		String gresponse = null;
		JsonArray jarr = new JsonArray();
		//logger.debug("In getAllEmployeesByStoreId......{}");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			employeeslist = hrMgmtService.getAllEmployeesByStoreId(storeId);
			Gson gson = new Gson();
			gresponse = gson.toJson(employeeslist);
		}

		// System.out.println("res:" + gresponse);
		return gresponse;
	}

	@RequestMapping(value = "/getEmployeeByStoreIdAndId", method = RequestMethod.GET)
	@ResponseBody
	public String getEmployeeByStoreIdAndId(@RequestHeader("storeId") Integer storeId, @RequestHeader("id") Integer id,
			HttpSession session) {
		Employee employee = null;
		JsonArray jarr = new JsonArray();
		//logger.debug("In getEmployeeByStoreIdAndId......{}");
		String gresponse = null;
		//logger.debug("In getAllEmployeesByStoreId......{}");
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			employee = hrMgmtService.getEmployeeByStoreIdAndId(storeId, id);
			String photourl = hrMgmtService.getEmployeeImageById(id);
			String docurl = hrMgmtService.getEmployeeDocImageById(id);
			if (photourl != null) {
				employee.setPhoto(photourl);
			}
			if (docurl != null) {
				employee.setIdProofDocImage(docurl);
			}
			Gson gson = new Gson();
			gresponse = gson.toJson(employee);
		}

		// System.out.println("res:" + gresponse);
		return gresponse;
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String UpdateEmployee(@RequestBody String employeeString, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		//logger.debug("In updateEmployee Controller......{}", employeeString);
		JsonObject jobj = new JsonObject();
		jobj.addProperty("empid", 0);
		// ModelAndView mav = new ModelAndView();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		LoginInfoByUserDTO userInfo = null;
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			// PrintWriter out = response.getWriter();
			// response.setContentType("text/plain");
			Gson gson = new GsonBuilder().create();
			Employee employee = gson.fromJson(employeeString, new TypeToken<Employee>() {
			}.getType());

			employee.setUpdatedBy(String.valueOf(userInfo.getId()));

			String res = hrMgmtService.updateEmployee(employee);
			Integer id = Integer.parseInt(res);
			if (id > 0) {
				jobj.addProperty("empid", id);
			} else {
				jobj.addProperty("empid", 0);
			}

		}
		return jobj.toString();
	}

	@RequestMapping(value = "/uploadEmpImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadEmpImage(@RequestParam("id") Integer empid, @RequestParam("file") MultipartFile fileUpload,
			HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		//logger.debug("in uploadEmpImage...! ");
		EmployeeFile empfile = new EmployeeFile();
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		InputStream inputFile = null;
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_upload", false);
		Boolean resp = false;
		byte[] byteArr = null;
		userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER);
		if (userInfo != null) {
			if (fileUpload != null && fileUpload.getSize() > 0 && CanFileUpload(fileUpload)) {
				try {
					//System.out.println("inputsterm:" + inputFile);
					//System.out.println("byte arr:" + byteArr);
					String storeIdString = String.valueOf(userInfo.getStoreId());

					String uploadsDir = "/uploads/";
					String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
					File pathtoupload = new File(realPathtoUploads);
					if (!pathtoupload.exists()) {
						pathtoupload.mkdir();
					}

					//System.out.println("path:" + realPathtoUploads);
					String orgName = fileUpload.getOriginalFilename();
					String filePath = realPathtoUploads + "/" + orgName;
					File dest = new File(filePath);
					fileUpload.transferTo(dest);
					String storeId = "'" + userInfo.getStoreId() + "'";
					empfile.setId(empid);
					empfile.setStoreId(storeId);
					empfile.setFileName(fileUpload.getOriginalFilename());
					FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
					filePart.setContentDisposition(
							FormDataContentDisposition.name("file").fileName(fileUpload.getOriginalFilename()).build());
					MultiPart multipartEntity = new FormDataMultiPart()
							.field("emp", new Gson().toJson(empfile), MediaType.APPLICATION_JSON_TYPE)
							.bodyPart(filePart);

					String response2 = hrMgmtService.uploadImage(multipartEntity);

					resp = response2.equalsIgnoreCase("Success") ? true : false;
					jobj.addProperty("is_upload", resp);
					// System.out.println("AFTER UPLOAD EMPLOYEE IMAGE RESPONSE
					// IS:" +
					// response2);
				} catch (Exception e) {
					logger.error("File uploading problem: ", e);
				}
			} else {
				jobj.addProperty("is_upload", false);
				jobj.addProperty("errormsg", "File Size Should Not Exceed 1Mb Size");
			}
		}
		return jobj.toString();
	}

	public boolean CanFileUpload(MultipartFile fileUpload) {
		Long fileSizeInMb = fileUpload.getSize() / (1024 * 1024);
		Boolean response = false;
		response = (fileSizeInMb <= 1) ? true : false;
		return response;
	}

	@RequestMapping(value = "/uploadEmpDocImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadEmpDocImage(@RequestParam("id") Integer empid, @RequestParam("docname") String docname,
			@RequestParam("file") MultipartFile fileUpload, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		//logger.debug("in uploadEmpImage...! ");
		EmployeeFile empfile = new EmployeeFile();
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		InputStream inputFile = null;
		JsonObject jobj = new JsonObject();
		jobj.addProperty("is_upload", false);
		Boolean resp = false;
		byte[] byteArr = null;
		userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER);
		if (userInfo != null) {
			if (fileUpload != null && fileUpload.getSize() > 0 && CanFileUpload(fileUpload)) {
				try {
					//System.out.println("inputsterm:" + inputFile);
					//System.out.println("byte arr:" + byteArr);
					String storeIdString = String.valueOf(userInfo.getStoreId());
					//System.out.println("DOCNAME TO BE UPLOADED IS:" + docname);
					String uploadsDir = "/uploads/";
					String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
					File pathtoupload = new File(realPathtoUploads);
					if (!pathtoupload.exists()) {
						pathtoupload.mkdir();
					}

					//System.out.println("path:" + realPathtoUploads);
					String orgName = fileUpload.getOriginalFilename();
					String filePath = realPathtoUploads + "/" + orgName;
					File dest = new File(filePath);
					fileUpload.transferTo(dest);
					String storeId = "'" + userInfo.getStoreId() + "'";
					empfile.setId(empid);
					empfile.setStoreId(storeId);
					empfile.setFileName(fileUpload.getOriginalFilename());
					empfile.setDocName(docname);
					FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
					filePart.setContentDisposition(
							FormDataContentDisposition.name("file").fileName(fileUpload.getOriginalFilename()).build());
					MultiPart multipartEntity = new FormDataMultiPart()
							.field("emp", new Gson().toJson(empfile), MediaType.APPLICATION_JSON_TYPE)
							.bodyPart(filePart);

					String response2 = hrMgmtService.uploadDocument(multipartEntity);

					resp = response2.equalsIgnoreCase("Success") ? true : false;
					jobj.addProperty("is_upload", resp);
					// System.out.println("AFTER UPLOAD EMPLOYEE IMAGE RESPONSE
					// IS:" +
					// response2);
				} catch (Exception e) {
					logger.error("File uploading problem: ", e);
				}
			} else {
				jobj.addProperty("is_upload", false);
				jobj.addProperty("errormsg", "File Size Should Not Exceed 1Mb Size");
			}
		}
		return jobj.toString();
	}

	@RequestMapping(value = "/loademptype", method = RequestMethod.GET)
	public ModelAndView viewemployeetype(Model model, HttpSession session) {
		//logger.debug("In loademptype......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}

		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {
			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_SETUP);
			menuselect.setChildsubMenu(Constant.HR_EMP_TYPE);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<EmployeeType> employeeTypeList = hrMgmtService.getAllEmployeeTypeByStoreId(userInfo.getStoreId());

			session.setAttribute("employeeTypeList", employeeTypeList);
			// mav.addObject(Constants.ADMIN, "Y");

			mav.setViewName(ForwardConstants.VIEW_HR_EMPLOYE_TYPE);
			// mav.setViewName("hr/dutyshift");
			return mav;
		}

	}

	@RequestMapping(value = "/employeetypedetails", method = RequestMethod.GET)
	@ResponseBody
	public String viewemployeedetails(HttpSession session) {
		//logger.debug("In employeedetails......");
		LoginInfoByUserDTO userInfo = null;
		JsonObject finaljobj = new JsonObject();
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			List<EmployeeType> employeeTypeList = hrMgmtService.getAllEmployeeTypeByStoreId(userInfo.getStoreId());

			List<Designation> desgList = hrMgmtService.getAllDesignationByStoreId(userInfo.getStoreId());

			List<Department> deptList = hrMgmtService.getAllDepartmentsByStoreId(userInfo.getStoreId());
			JsonArray jarr = new JsonArray();

			Gson gson = new Gson();
			String gresponse = gson.toJson(employeeTypeList);

			JsonArray jarr_emptypelist = new JsonArray();
			JsonArray jarr_designationlist = new JsonArray();
			JsonArray jarr_departmentlist = new JsonArray();
			for (EmployeeType obj : employeeTypeList) {
				JsonObject employeeTypeListjobj = new JsonObject();
				employeeTypeListjobj.addProperty("emptypelistid", obj.getId());
				employeeTypeListjobj.addProperty("storeId", obj.getStoreId());
				employeeTypeListjobj.addProperty("emptype", obj.getType());
				employeeTypeListjobj.addProperty("casual_leave", obj.getCasualLeave());
				employeeTypeListjobj.addProperty("sick_leave", obj.getSickLeave());
				employeeTypeListjobj.addProperty("misc_leave", obj.getMiscLeave());
				jarr_emptypelist.add(employeeTypeListjobj);
			}
			for (Designation obj : desgList) {
				JsonObject desgListjobj = new JsonObject();
				desgListjobj.addProperty("desgId", obj.getId());
				desgListjobj.addProperty("desgname", obj.getName());
				desgListjobj.addProperty("storeId", obj.getStoreId());
				jarr_designationlist.add(desgListjobj);
			}
			for (Department obj : deptList) {
				JsonObject deptListjobj = new JsonObject();
				deptListjobj.addProperty("deptId", obj.getId());
				deptListjobj.addProperty("deptName", obj.getName());
				deptListjobj.addProperty("storeId", obj.getStoreId());
				jarr_departmentlist.add(deptListjobj);
			}
			//System.out.println("res:" + gresponse);
			finaljobj.add("emptypelistjson", jarr_emptypelist);
			finaljobj.add("designationlistjson", jarr_designationlist);
			finaljobj.add("departmentlistjson", jarr_departmentlist);
			return finaljobj.toString();

		} else {

			return finaljobj.toString();

		}

	}

	@RequestMapping(value = "/loademp", method = RequestMethod.GET)
	public ModelAndView viewmemployeedetails(Model model, HttpSession session) {
		//logger.debug("In loademp......");
		ModelAndView mav = new ModelAndView();
		LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
			mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
			return mav;
		} else {
			MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_SETUP);
			menuselect.setChildsubMenu(Constant.HR_EMP);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			mav.setViewName(ForwardConstants.VIEW_HR_EMPLOYEE_DETAILS);
			return mav;
		}

	}

	@RequestMapping(value = "/getEmployeeLeaves", method = RequestMethod.GET)
	@ResponseBody
	public String getEmployeeLeaves(@RequestHeader("storeId") Integer storeId, HttpSession session) {
		List<EmployeeLeave> employeesleavelist = null;
		String gresponse = null;
		LoginInfoByUserDTO userInfo = null;
		JsonArray jarr = new JsonArray();
		//logger.debug("In getEmployeeLeaves......{}");
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) != null) {
			employeesleavelist = hrMgmtService.getEmployeeLeaves(storeId);
			Gson gson = new Gson();
			gresponse = gson.toJson(employeesleavelist);
			//logger.debug("response string in getEmployeeLeaves METHOD AND IN HRMANAGEMENT CONTROLLER getEmployeeLeaves bystoreid ...{}",gresponse);
		}
		// System.out.println("res:" + gresponse);
		return gresponse;
	}

	
	@RequestMapping(value = "/loadleaveregister", method = RequestMethod.GET)
	  public ModelAndView loadleaveregister(Model model, HttpSession session) {
	    ModelAndView mav = new ModelAndView();
	    LoginInfoByUserDTO userInfo = null;
		String lang = null;
		if ((lang = (String) session.getAttribute(Constant.SES_LOGGED_IN_LANG)) == null) {
			lang = Constant.DEFAULT_LANG;
		}
	   
		if ((userInfo = (LoginInfoByUserDTO) session.getAttribute(Constant.SES_LOGGED_IN_USER)) == null) {
	      mav.setViewName(ForwardConstants.REDIRECT_LOGIN_PAGE);
	      return mav;
	    } else {
	    	MenuByUserDTO menuByUserDTO = roleService.getRoleMenuByUser(userInfo.getCompanyId(),
					(Integer) session.getAttribute(Constant.SES_LOGGED_IN_STORE_ID), userInfo.getId(),
					userInfo.getProductTypeId(), RoleBasedCostants.UNIT_SETUP_201B, lang);
			MenuSelection menuselect = new MenuSelection();
			menuselect.setMenu(Constant.HR);
			menuselect.setSubMenu(Constant.HR_LEAVE_REGISTER);
			// menuselect.setChildsubMenu(Constant.HR_DUTYSHIFT);
			mav.addObject("menuselect", menuselect);
			mav.addObject("menuByUserDTO", menuByUserDTO);
			List<DutyShift> dutyList = hrMgmtService.getAllDutyShiftByStoreId(userInfo.getStoreId());
			session.setAttribute("dutyShiftList", dutyList);
	      mav.setViewName(ForwardConstants.VIEW_HR_EMPLOYEE_LEAVE_DETAILS);

	    }
	    return mav;
	  }
	 
	  


	

}