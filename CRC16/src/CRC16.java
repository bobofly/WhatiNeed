import java.net.StandardSocketOptions;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CRC16 {

	static byte[] crc16_tab_h = { (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
			(byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
			(byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40 };

	static byte[] crc16_tab_l = { (byte) 0x00, (byte) 0xC0, (byte) 0xC1, (byte) 0x01, (byte) 0xC3, (byte) 0x03, (byte) 0x02, (byte) 0xC2, (byte) 0xC6, (byte) 0x06, (byte) 0x07, (byte) 0xC7, (byte) 0x05, (byte) 0xC5, (byte) 0xC4, (byte) 0x04, (byte) 0xCC, (byte) 0x0C, (byte) 0x0D, (byte) 0xCD, (byte) 0x0F, (byte) 0xCF, (byte) 0xCE, (byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB, (byte) 0x0B, (byte) 0xC9, (byte) 0x09, (byte) 0x08, (byte) 0xC8, (byte) 0xD8, (byte) 0x18, (byte) 0x19, (byte) 0xD9, (byte) 0x1B, (byte) 0xDB, (byte) 0xDA, (byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF, (byte) 0x1F, (byte) 0xDD, (byte) 0x1D, (byte) 0x1C, (byte) 0xDC, (byte) 0x14, (byte) 0xD4, (byte) 0xD5, (byte) 0x15, (byte) 0xD7, (byte) 0x17, (byte) 0x16, (byte) 0xD6, (byte) 0xD2, (byte) 0x12,
			(byte) 0x13, (byte) 0xD3, (byte) 0x11, (byte) 0xD1, (byte) 0xD0, (byte) 0x10, (byte) 0xF0, (byte) 0x30, (byte) 0x31, (byte) 0xF1, (byte) 0x33, (byte) 0xF3, (byte) 0xF2, (byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7, (byte) 0x37, (byte) 0xF5, (byte) 0x35, (byte) 0x34, (byte) 0xF4, (byte) 0x3C, (byte) 0xFC, (byte) 0xFD, (byte) 0x3D, (byte) 0xFF, (byte) 0x3F, (byte) 0x3E, (byte) 0xFE, (byte) 0xFA, (byte) 0x3A, (byte) 0x3B, (byte) 0xFB, (byte) 0x39, (byte) 0xF9, (byte) 0xF8, (byte) 0x38, (byte) 0x28, (byte) 0xE8, (byte) 0xE9, (byte) 0x29, (byte) 0xEB, (byte) 0x2B, (byte) 0x2A, (byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F, (byte) 0xEF, (byte) 0x2D, (byte) 0xED, (byte) 0xEC, (byte) 0x2C, (byte) 0xE4, (byte) 0x24, (byte) 0x25, (byte) 0xE5, (byte) 0x27, (byte) 0xE7,
			(byte) 0xE6, (byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3, (byte) 0x23, (byte) 0xE1, (byte) 0x21, (byte) 0x20, (byte) 0xE0, (byte) 0xA0, (byte) 0x60, (byte) 0x61, (byte) 0xA1, (byte) 0x63, (byte) 0xA3, (byte) 0xA2, (byte) 0x62, (byte) 0x66, (byte) 0xA6, (byte) 0xA7, (byte) 0x67, (byte) 0xA5, (byte) 0x65, (byte) 0x64, (byte) 0xA4, (byte) 0x6C, (byte) 0xAC, (byte) 0xAD, (byte) 0x6D, (byte) 0xAF, (byte) 0x6F, (byte) 0x6E, (byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B, (byte) 0xAB, (byte) 0x69, (byte) 0xA9, (byte) 0xA8, (byte) 0x68, (byte) 0x78, (byte) 0xB8, (byte) 0xB9, (byte) 0x79, (byte) 0xBB, (byte) 0x7B, (byte) 0x7A, (byte) 0xBA, (byte) 0xBE, (byte) 0x7E, (byte) 0x7F, (byte) 0xBF, (byte) 0x7D, (byte) 0xBD, (byte) 0xBC, (byte) 0x7C, (byte) 0xB4, (byte) 0x74,
			(byte) 0x75, (byte) 0xB5, (byte) 0x77, (byte) 0xB7, (byte) 0xB6, (byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3, (byte) 0x73, (byte) 0xB1, (byte) 0x71, (byte) 0x70, (byte) 0xB0, (byte) 0x50, (byte) 0x90, (byte) 0x91, (byte) 0x51, (byte) 0x93, (byte) 0x53, (byte) 0x52, (byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57, (byte) 0x97, (byte) 0x55, (byte) 0x95, (byte) 0x94, (byte) 0x54, (byte) 0x9C, (byte) 0x5C, (byte) 0x5D, (byte) 0x9D, (byte) 0x5F, (byte) 0x9F, (byte) 0x9E, (byte) 0x5E, (byte) 0x5A, (byte) 0x9A, (byte) 0x9B, (byte) 0x5B, (byte) 0x99, (byte) 0x59, (byte) 0x58, (byte) 0x98, (byte) 0x88, (byte) 0x48, (byte) 0x49, (byte) 0x89, (byte) 0x4B, (byte) 0x8B, (byte) 0x8A, (byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F, (byte) 0x4F, (byte) 0x8D, (byte) 0x4D,
			(byte) 0x4C, (byte) 0x8C, (byte) 0x44, (byte) 0x84, (byte) 0x85, (byte) 0x45, (byte) 0x87, (byte) 0x47, (byte) 0x46, (byte) 0x86, (byte) 0x82, (byte) 0x42, (byte) 0x43, (byte) 0x83, (byte) 0x41, (byte) 0x81, (byte) 0x80, (byte) 0x40 };

	static byte[] data={0x5a,(byte) 0xa5,0x01,0x02,0x03,0x04,0x5a,0x06,0x3c,(byte) 0xc3};//����Ϊ10
	static byte[] SD_Data ={
			0x44,0x03,0x5a,(byte)0xa5,  //ͷ
			0x15,0x33,0x44,0x03,
			(byte)0xe3,
			0x04,
			(byte)0x8e,
			0x00,0x3b,0x3c,
			0x5a,0x3c,0x00,0x00,
			(byte)0xfa,
			0x01,
			(byte)0x90,
			0x00,0x00,0x00,
			0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,
			0x00,0x31,0x04,0x37,0x01,0x00,0x00,0x68,0x00,(byte) 0x88,
			0x3c,(byte) 0xc3   //β
			};
	
	public static int flag_index=0;
	/**
	 * ����CRC16У��
	 * 
	 * @param data
	 *            ��Ҫ���������
	 * @return CRC16У��ֵ
	 */
	public static int calcCrc16(byte[] data) {
		return calcCrc16(data, 0, data.length);
	}

	/**
	 * ����CRC16У��
	 * 
	 * @param data
	 *            ��Ҫ���������
	 * @param offset
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 * @return CRC16У��ֵ
	 */
	public static int calcCrc16(byte[] data, int offset, int len) {
		return calcCrc16(data, offset, len, 0xffff);
	}

	/**
	 * ����CRC16У��
	 * 
	 * @param data
	 *            ��Ҫ���������
	 * @param offset
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 * @param preval
	 *            ֮ǰ��У��ֵ
	 * @return CRC16У��ֵ
	 */
	public static int calcCrc16(byte[] data, int offset, int len, int preval) {
		int ucCRCHi = (preval & 0xff00) >> 8;
		int ucCRCLo = preval & 0x00ff;
		int iIndex;
		for (int i = 0; i < len; ++i) {
			iIndex = (ucCRCLo ^ data[offset + i]) & 0x00ff;
			ucCRCLo = ucCRCHi ^ crc16_tab_h[iIndex];
			ucCRCHi = crc16_tab_l[iIndex];
		}
		return ((ucCRCHi & 0x00ff) << 8) | (ucCRCLo & 0x00ff) & 0xffff;
	}
	
	//byte������ʵ����
	public static int getRealLength(byte[] b){
		int i=0;
		for(;i<b.length;i++){
			if(b[i]=='\0')
				break;
		}
    	return i;
    }
	
	public static int SearchIndex(byte[] b,int i1,int i2, int flag_index){
//		 public static int SearchIndex(byte[] b,int i1,int i2){
		
		int m=0;
		int n = 0;
		
		//��flag_index��bufĩβ
	  	for(m=flag_index;m<b.length;m++){
	  		
	  		if(m<b.length-1){ //�뱣֤b[m+1]�Ϸ�����m+1<buf_data.length,��m<buf_data.length-1
	  			if(b[m]==i1 && b[m+1]==i2){
	  				break;
	  			}
	  		}
	  		else if(m==b.length-1){ //�������,i1Ϊbuf_data���һ��Ԫ��,��ʱ���ж�i2�Ƿ�Ϊ���һ��
	  			if(b[m]==i1&&b[0]==i2)  {
	  				break;
	  			}
	  		}
	  	}	  
	  	
	  	//��buf��ʼ��flag_index
	  	for(m=0;m<flag_index;m++){
	  		
	  		if(b[m]==i1 && b[m+1]==i2){
	  			break;
	  		}
	  	}
	  	
	  	if(m==flag_index){   //������
	  		m=-1;   
	  	}
	  	
	  	return m;
	  	
	 }
	
		//���ɲ�������ָ���
		public static byte[] CreateFrame(int cmd_h,int cmd_l){
						
			byte[] framebyte=new byte[9];
						
			//֡ͷ
			framebyte[0]=0x5a;
			framebyte[1]=(byte) 0xa5;
			//֡β
			framebyte[7]=0x3c;
			framebyte[8]=(byte) 0xc3;
			//���ݳ���
			framebyte[2]=0x04;
			//ָ��߰�λ
			framebyte[3]=(byte) cmd_h;
			//ָ��Ͱ�λ
			framebyte[4]=(byte) cmd_l;
			//CRCУ����
//			byte[] crcbyte=new byte[2];
//			crcbyte[0]=framebyte[3];
//			crcbyte[1]=framebyte[4];
//			int crc = CRC16.calcCrc16(crcbyte);
			int crc = CRC16.calcCrc16(new byte[] {framebyte[3],framebyte[4]});
			framebyte[5]=(byte) (crc&0x00ff);      //crcУ��Ͱ�λ
			framebyte[6]=(byte) ((crc&0xff00)>>8); //crcУ��߰�λ
						
			return framebyte;			
		}
		
		//���ɴ�����ָ���
		public static byte[] CreateDataFrame(int cmd_h,int cmd_l,int data_h,int data_l){
								
			byte[] framebyte=new byte[11];
								
			//֡ͷ
			framebyte[0]=0x5a;
			framebyte[1]=(byte) 0xa5;
			//֡β
			framebyte[9]=0x3c;
			framebyte[10]=(byte) 0xc3;
			//���ݳ���
			framebyte[2]=0x06;
			//ָ��߰�λ
			framebyte[3]=(byte) cmd_h;
			//ָ��Ͱ�λ
			framebyte[4]=(byte) cmd_l;
			//���ݸ߰�λ
			framebyte[5]=(byte) data_h;
			//���ݵͰ�λ
			framebyte[6]=(byte) data_l;
			//CRCУ����
			int crc = CRC16.calcCrc16(new byte[] {framebyte[3],framebyte[4],framebyte[5],framebyte[6]});
			framebyte[7]=(byte) (crc&0x00ff);      //crcУ��Ͱ�λ
			framebyte[8]=(byte) ((crc&0xff00)>>8); //crcУ��߰�λ
								
			return framebyte;			
		}
		
		//��������������
		   private static byte[] CreateHeartByte(int i){
				
				byte[] heart_byte=new byte[9];
				
				//֡ͷ
				heart_byte[0]=0x5a;
				heart_byte[1]=(byte) 0xa5;
				//֡β
				heart_byte[7]=0x3c;
				heart_byte[8]=(byte) 0xc3;
				//���ݳ���
				heart_byte[2]=0x04;
				//ָ��  ��cmd��ɸ߰ˣ�����[3]���͵Ͱ�λ(����[4])
				heart_byte[3]=(byte) ((i&0xff00)>>8);
				heart_byte[4]=(byte) (i&0x00ff);
				//CRCУ����
				byte[] heart_byte_cmd=new byte[2];
				heart_byte_cmd[0]=(byte) ((i&0xff00)>>8);
				heart_byte_cmd[1]=(byte) (i&0x00ff);
				int crc = CRC16.calcCrc16(heart_byte_cmd);
				heart_byte[5]=(byte) (crc&0x00ff);
				heart_byte[6]=(byte) ((crc&0xff00)>>8);
				
				return heart_byte;
				
			}
		//��ָ��byte������16���Ƶ���ʽ��ӡ������̨
	    public static void printHexString( byte[] b) {    
	       for (int j = 0; j < b.length; j++) {   
	         String hex = Integer.toHexString(b[j] & 0xFF);   
	         if (hex.length() == 1) {   
	           hex = '0' + hex;   
	         }   
	         System.out.print(hex.toUpperCase()+" ");   
	       }  
	       System.out.println("\n");
	      
	    }
	    
		
	// ����
	public static void main(String[] args) {
		// 0x02 05 00 03 FF 00 , crc16=7C 09
//		int crc = CRC16.calcCrc16(new byte[] {0x04,0x00,30,50});
//		System.out.println("����CRCУ�飺");
//		System.out.print(String.format("%x",crc&0x00ff));
//		System.out.println(String.format("%x",(crc&0xff00)>>8));
		
//		printHexString(CreateFrame(0x03,0x03));
//		printHexString(CreateDataFrame(0x04,0x00,0x30,0x50));
		
//		flag_index=8;
//		int m=SearchIndex(SD_Data, 0x5a, (byte)0xa5,flag_index);
//		System.out.println("��SD_Data�дӵ�"+flag_index+"��λ�ÿ�ʼ����һ��ͷ��λ��Ϊ��"+m);
		
//		int i=8;
//		String h_s;
//        if(i<=9 && i>=0){
//        	h_s="0"+i;
//        }else{
//        	 h_s = String.valueOf(i);  
//        }
//        	
//           //ʱ
//		System.out.println("h_sΪ"+h_s);
		
//		 SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
//         long diff = 0;
//         //�������ʱ��ĺ���ʱ�����
//         try {
//			diff = format.parse("2017/01/21   09:30:24").getTime() -
//					 format.parse("2017/01/20   20:50:15").getTime();
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         int second=(int) (diff/1000);
//         System.out.println("������"+second);
//        double c=15*360000/second/100.00;
//        System.out.println("nahi="+c);
		
		StringBuilder sb = new StringBuilder();
		char[] dataU8s = getU8s();
		for (int i = 0; i < 4; i++) {
			String hexString = convertU8ToHexString(dataU8s[i]);
			sb.append("0x");
			if (hexString.length() == 1) {
				sb.append("0");
			}
			sb.append(hexString).append(" ");
		}
		System.out.println(sb);
		
	}
	
	public static char[] getU8s() {
		char[] guidesU8s = new char[4];
		guidesU8s[0] = 515;
		guidesU8s[1] = 514;
		guidesU8s[2] = 513;
		guidesU8s[3] = 512;
		return guidesU8s;
	}
	
	public static String convertU8ToHexString(char u8) {
		return Integer.toHexString(u8);
	}
	
	
}
