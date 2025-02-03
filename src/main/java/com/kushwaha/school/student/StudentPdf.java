package com.kushwaha.school.student;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Map;

@Service
public class StudentPdf {
    private static final String FILE_NAME = "C:\\Users\\msi\\Desktop\\registrationpdf.pdf";

    public ResponseEntity<?> generateStudentPdf(StudentPdfDto dto) {
        try{
            FileInputStream fis = new FileInputStream(FILE_NAME);
            PdfReader reader = new PdfReader(fis);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(reader, writer);

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);
            Map<String, PdfFormField> fields = form.getAllFormFields();
            fields.get("full_name").setValue(dto.fullName());
            fields.get("home_address").setValue(dto.homeAddress());
            fields.get("place_of_birth").setValue(dto.placeOfBirth());
            fields.get("city").setValue(dto.city());
            fields.get("zipcode").setValue(dto.zipcode());
            fields.get("phone_number").setValue(dto.phoneNumber());
            fields.get("email").setValue(dto.email());
            fields.get("birth_year").setValue(String.valueOf(dto.dob().getYear()));
            fields.get("birth_month").setValue(String.valueOf(dto.dob().getMonthValue()));
            fields.get("birth_day").setValue(String.valueOf(dto.dob().getDayOfMonth()));
            var radioField = fields.get("gender");
            if("Male".equalsIgnoreCase(dto.gender())){
                radioField.setValue("male");
            }
            else{
                radioField.setValue("female");
            }
            form.flattenFields();
            pdfDocument.close();
            ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_details.pdf") // for downloading into system
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=student_details.pdf") // it will open pdf into browser
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
