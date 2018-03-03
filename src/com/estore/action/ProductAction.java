package com.estore.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;





public class ProductAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService productService;


    private File upload;// 上传的文件
    private String uploadFileName;//上传的文件名陈



	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String findById()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		Product product = productService.findById(id);
		ServletActionContext.getRequest().setAttribute("p", product);
		return "productinfo";
	}
	public String findAll()
	{
		// 1.查询所有商品
		List<Product> ps = productService.findAll();

		// 2.跳转到page.jsp页面
		ServletActionContext.getRequest().setAttribute("ps", ps);
		return "allpage";
	}
	public String isprivilege()
	{
		Cookie[] cookie = ServletActionContext.getRequest().getCookies();
    	String ticket = null;
    	for(int i=0;i<cookie.length;i++)
    	{
    		Cookie cook = cookie[i];
    		if(cook.getName().equalsIgnoreCase("ticket"))
    		{
    			ticket = cook.getValue();
    			break;
    		}
    	}
    	System.out.println(ticket);
    	if(productService == null)
    	{
    		System.out.print("dsafd");
    	}
    	return productService.isprivilege(ticket);
	}
	public String addproduct()
	{
		System.out.println(uploadFileName);
		Product product = new Product();
		String name = ServletActionContext.getRequest().getParameter("name");
		String price = ServletActionContext.getRequest().getParameter("price");
		String pnum = ServletActionContext.getRequest().getParameter("pnum");
		String description = ServletActionContext.getRequest().getParameter("description");
		String category = ServletActionContext.getRequest().getParameter("category");
		product.setName(name);
		product.setCategory(category);
		product.setDescription(description);
		product.setPrice(Double.parseDouble(price));
		product.setPnum(Integer.parseInt(pnum));
        if (upload != null) {
            // 获得文件上传的磁盘绝对路径
            try {
                String realPath = ServletActionContext.getServletContext()
                        .getRealPath("/temp");
                
                // 创建一个文件
                String productname = UUID.randomUUID().toString().substring(0, 6);
                File diskFile = new File(realPath + File.separator
                        + productname+".jpg");
                // 文件上传,使用FileUtils工具类
                FileUtils.copyFile(upload, diskFile);
                product.setImgurl("/temp/" + productname+".jpg");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 封装商品的id
        product.setId(UUID.randomUUID().toString());
        System.out.println(product);
        productService.save(product);
        return "add_success";

	}
	public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }


	@Override
	public String execute() throws Exception {
		String method = ServletActionContext.getRequest().getParameter("method");
		if ("findById".equals(method)) {
			// 根据id查找商品
			return findById();
		} else if ("findAll".equals(method) || method == null) {
			// 查找全部商品
			return findAll();
		}else if("isprivilege".equals(method))
			return isprivilege();
		else if("addproduct".equals(method))
			return addproduct();
		return null;
	}
	
	
	
}
