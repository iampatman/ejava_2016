/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.ft02.servlet;

import iss.ft02.business.PodBean;
import iss.ft02.entity.Pod;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author NguyenTrung
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @EJB PodBean podBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String note = new String(readPart(request.getPart("note")));
        String time = new String(readPart(request.getPart("time")));
        String podId = new String(readPart(request.getPart("podId")));                
        byte[] image = readPart(request.getPart("image"));
        Pod pod = new Pod();
        pod.setImage(image);
        pod.setPodId(Integer.valueOf(podId));
        pod.setNote(note);
        System.out.println(">>>> Time: " + time);
        pod.setDeliveryDate(new Timestamp(Long.valueOf(time)));
        System.out.println(">>>>>>>>>> image size: " + image.length);
        System.out.println(" >>>>>>>>>>> new pod: " + pod.toString());
        podBean.update(pod);
//        
//        try (PrintWriter pw = response.getWriter()){
//            pw.print(pod.toString());
//        }
        
    }

    private byte[] readPart(Part p) throws IOException {
        byte[] buffer = new byte[1024 * 20];
        int sz = 0;
        try (InputStream is = p.getInputStream()) {
            //Get the part using the name
            BufferedInputStream bis = new BufferedInputStream(is);
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                while (-1 != (sz = bis.read(buffer))) {
                    baos.write(buffer, 0, sz);
                }
                buffer = baos.toByteArray();
            }
        }
        return (buffer);
    }

}
