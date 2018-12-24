/*package com.amazon.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Practice
{
	String excelFilePath = "C:\\Users\\home\\Downloads\\eclipse-jee-mars-2-win32\\logindetails.xlsx";
	public void readExcel() throws IOException
	{
		
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        
        int rowCount = firstSheet.getPhysicalNumberOfRows();
        
        
        System.out.println(rowCount);
        for(int i=0;i<=rowCount;i++)
        {
        	 for(int j=0;j<3;j++)
             {
        		 String cellVal = firstSheet.getRow(i).getCell(j).getStringCellValue();
        		 System.out.println(cellVal);
             }
        }
        
        
          Iterator<Row> iterator = firstSheet.iterator();   
        
       while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
         
        workbook.close();
        inputStream.close();
	}
	
	public void writeExcel()
	{
		    XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
	        Object[][] datatypes = {
	                {"Datatype", "Type", "Size(in bytes)"},
	                {"int", "Primitive", 2},
	                {"float", "Primitive", 4},
	                {"double", "Primitive", 8},
	                {"char", "Primitive", 1},
	                {"String", "Non-Primitive", "No fixed size"}
	        };

	        int rowNum = 0;
	        System.out.println("Creating excel");

	        for (Object[] datatype : datatypes) {
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : datatype) {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	        }

	        try {
	            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Done");
	
}
	
	public void testUnique()
	{
		List<String> list = new ArrayList<String>();
		list.add("Daman");
		list.add("deep");
		list.add("Singh");
		
		Iterator<String> itr = list.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
			
		}
		
		System.out.println("------------");
		Hashtable<Integer, String> hash = new Hashtable<Integer, String>();
		hash.put(1,"A");
		hash.put(2, "B");
		hash.put(3,"C");
		hash.put(3, "D");
		hash.put(4,"E");
		
		 Set<Integer> keys = hash.keySet();
		 Iterator<Integer> hashItr = keys.iterator();
		 
		 while(hashItr.hasNext())
		 {
			 int i = hashItr.next();
			 System.out.println(hash.get(i));
		 }
		
		 HashMap<Integer, String> hashM = new HashMap<Integer, String>();
		 hashM.put(1,"A");
		 hashM.put(2, "B");
		 hashM.put(3,"C");
		 hashM.put(3, "D");
		 hashM.put(4,"E");
			
			 Set<Integer> keyM = hashM.keySet();
			 Iterator<Integer> hashMItr = keyM.iterator();
			 System.out.println("-----------------");
			 while(hashMItr.hasNext())
			 {
				 int iM = hashMItr.next();
				 System.out.println(hashM.get(iM));
			 }
			
		 
		
		
		
	}
	
	public static void main(String[] str) throws IOException, InterruptedException
	{
		Practice prac = new Practice();
	//	prac.readExcel();
	//	Thread.sleep(5000);
	//	prac.writeExcel();
		
		prac.testUnique();
	}
}
*/