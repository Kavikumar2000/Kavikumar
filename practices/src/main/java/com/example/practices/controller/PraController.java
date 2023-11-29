package com.example.practices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;

@RestController
public class PraController {
	@GetMapping("/convertDocxToPdf")
	public String convertDocxToPdf(){
		String tmpInputFileNameWithPath = null;
		try {
			Credentials credentials = Credentials.servicePrincipalCredentialsBuilder().withClientId("c5de7268f7a54cfea810ebdb4bce9279")
					.withClientSecret("p8e-Z-dVlWLbredUzB1YggC2Q11R4jXxBvZb").build();
			String inputFileNameWithPath = "D:\\SpringPractice\\input.docx";
			ExecutionContext executionContext = ExecutionContext.create(credentials);
			CreatePDFOperation createPdfOperation = CreatePDFOperation.createNew();
			FileRef sourceFileRef = FileRef.createFromLocalFile(inputFileNameWithPath);
			createPdfOperation.setInput(sourceFileRef);
			FileRef result = createPdfOperation.execute(executionContext);
			tmpInputFileNameWithPath = inputFileNameWithPath.replace(".docx", ".pdf");
			result.saveAs(tmpInputFileNameWithPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpInputFileNameWithPath;
	}

}
