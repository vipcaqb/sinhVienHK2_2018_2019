package xuly;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author Ahihi
 *
 */
public class XuLy {

	public ArrayList<String> docFile() {
		ArrayList<String> ketQua = new ArrayList<String>();
		try {
			FileReader f = new FileReader("input.txt");
			BufferedReader in= new BufferedReader(f);
			while(true) {
				String st = in.readLine();
				if ( st == ""|| st == null) break;
				ketQua.add(st);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	public boolean mailHopLe(String mail) {
		int demACong=0,demCham=0,length=mail.length();
		for(int i=0;i<length;i++) {
			if(mail.charAt(i)=='@') demACong++;
			if(mail.charAt(i)=='.') demCham++;
		}
		return ((demACong==1)&&(demCham>=1));
	}
	public boolean namNhuan(int nam) {
		return ((nam%400==0)||((nam%4==0)&&(nam%100!=0)));
	}
	public boolean ngayHopLe(String date) {
		String []st = date.trim().split("[/]");
		int dd,MM,yyyy;
		dd=Integer.parseInt(st[0]);
		MM=Integer.parseInt(st[1]);
		yyyy=Integer.parseInt(st[2]);
		if(MM>12||MM<1||dd<1) return false;
		if(namNhuan(yyyy)) {
			if(MM==1||MM==3||MM==5||MM==7||MM==8||MM==10||MM==12) {
				if(dd>31) return false;
			}
			else if(MM==2) {
				if(dd>29) return false;
			}
			else if(MM==4||MM==6||MM==9||MM==11) {
				if(dd>30) return false;
			}
		}
		else {
			if(MM==1||MM==3||MM==5||MM==7||MM==8||MM==10||MM==12) {
				if(dd>31) return false;
			}
			else if(MM==2) {
				if(dd>28) return false;
			}
			else if(MM==4||MM==6||MM==9||MM==11) {
				if(dd>30) return false;
			}
		}
		return true;
	}
	public boolean tuoiHopLe(String tuoi) {
		int tuoiInt = Integer.parseInt(tuoi);
		return tuoiInt<=4;
	}
	/*Cau 2
	 * */
	public void ktDuLieuDauVao() {
		ArrayList<String > ds = docFile();
		int soDong=0;
		for(String t : ds) {
			int F=0;
			String []tam = t.split("[;]");
			String thongBao = "Dong "+(soDong+1)+":";
			if(mailHopLe(tam[3])==false) {
				thongBao +=" sai mail";
				F=1;
			}
			if(ngayHopLe(tam[1])==false) {
				if(F==1) thongBao+=",";
				thongBao+= " sai dinh dang ngay";
				F=1;
			}
			int length= tam.length;
			for(int i=4;i<length;i++) {
				if(tuoiHopLe(tam[i])==false) {
					if(F==1) thongBao+=",";
					thongBao+=" do tuoi qua 4";
					F=1;
					break;
				}
			}
			if(F==1) System.out.println(thongBao);
			soDong++;
		}
	}
	/*Cau 3
	 * */
	public int tuoiHon17(String giaDinh) {
		String []tam = giaDinh.split("[;]");
		int length= tam.length;
		int dem=0;
		for(int i=4;i<length;i++) {
			if(Integer.parseInt(tam[i])>17) dem++;
		}
		return dem;
	}
	public void thongKe() {
		try {
			ArrayList<String> ds = docFile();
			FileOutputStream fw = new FileOutputStream("output.txt");
			DataOutputStream writer = new DataOutputStream(fw);
			for(String t:ds) {
				String []tam = t.split("[;]");
				System.out.println(tam[0]+","+tam[2]+","+tuoiHon17(t));
				writer.writeBytes(tam[0]+","+tam[2]+","+tuoiHon17(t)+"\r\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
