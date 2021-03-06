#ifdef SerialUSB
#  define OUT SerialUSB
#else
#  define OUT Serial
#endif

const byte sampleSysexResponse[] = {

0xf0, 0xbf, 0x7c, 0x01, 0xbf, 0x7d, 0x17, 0xbf, 0x7e, 0x16, 0xb0, 0x01, 0x42, 0xb0, 0x02, 0x00, 0xb0, 0x03, 0x7f, 0xb0, 0x04, 0x5f, 0xb0, 0x05, 0x7f, 0xb0, 0x06, 0x0c, 0xb0, 0x07, 0x00, 0xb0, 0x08, 0x00, 0xb0, 0x09, 0x26, 0xb0, 0x0a, 0x16, 0xb0, 0x0b, 0x00, 0xb0, 0x0c, 0x00, 0xb0, 0x0d, 0x00, 0xb0, 0x0e, 0x00, 0xb1, 0x01, 0x39, 0xb1, 0x02, 0x00, 0xb1, 0x03, 0x7f, 0xb1, 0x04, 0x00, 0xb1, 0x05, 0x7f, 0xb1, 0x06, 0x0c, 0xb1, 0x07, 0x00, 0xb1, 0x08, 0x00, 0xb1, 0x09, 0x2f, 0xb1, 0x0a, 0x17, 0xb1, 0x0b, 0x0c, 0xb1, 0x0c, 0x00, 0xb1, 0x0d, 0x00, 0xb1, 0x0e, 0x00, 0xb2, 0x01, 0x39, 0xb2, 0x02, 0x00, 0xb2, 0x03, 0x7f, 0xb2, 0x04, 0x00, 0xb2, 0x05, 0x7f, 0xb2, 0x06, 0x0c, 0xb2, 0x07, 0x00, 0xb2, 0x08, 0x00, 0xb2, 0x09, 0x27, 0xb2, 0x0a, 0x17, 0xb2, 0x0b, 0x0c, 0xb2, 0x0c, 0x00, 0xb2, 0x0d, 0x00, 0xb2, 0x0e, 0x00, 0xb3, 0x01, 0x39, 0xb3, 0x02, 0x00, 0xb3, 0x03, 0x7f, 0xb3, 0x04, 0x00, 0xb3, 0x05, 0x7f, 0xb3, 0x06, 0x0c, 0xb3, 0x07, 0x00, 0xb3, 0x08, 0x00, 0xb3, 0x09, 0x30, 0xb3, 0x0a, 0x17, 0xb3, 0x0b, 0x0c, 0xb3, 0x0c, 0x00, 0xb3, 0x0d, 0x00, 0xb3, 0x0e, 0x00, 0xb4, 0x01, 0x39, 0xb4, 0x02, 0x00, 0xb4, 0x03, 0x7f, 0xb4, 0x04, 0x00, 0xb4, 0x05, 0x7f, 0xb4, 0x06, 0x0c, 0xb4, 0x07, 0x00, 0xb4, 0x08, 0x00, 0xb4, 0x09, 0x39, 0xb4, 0x0a, 0x17, 0xb4, 0x0b, 0x0c, 0xb4, 0x0c, 0x00, 0xb4, 0x0d, 0x00, 0xb4, 0x0e, 0x00, 0xb5, 0x01, 0x39, 0xb5, 0x02, 0x00, 0xb5, 0x03, 0x7f, 0xb5, 0x04, 0x00, 0xb5, 0x05, 0x7f, 0xb5, 0x06, 0x0c, 0xb5, 0x07, 0x00, 0xb5, 0x08, 0x00, 0xb5, 0x09, 0x2e, 0xb5, 0x0a, 0x17, 0xb5, 0x0b, 0x0c, 0xb5, 0x0c, 0x00, 0xb5, 0x0d, 0x00, 0xb5, 0x0e, 0x00, 0xb6, 0x01, 0x39, 0xb6, 0x02, 0x00, 0xb6, 0x03, 0x7f, 0xb6, 0x04, 0x00, 0xb6, 0x05, 0x7f, 0xb6, 0x06, 0x0c, 0xb6, 0x07, 0x00, 0xb6, 0x08, 0x00, 0xb6, 0x09, 0x37, 0xb6, 0x0a, 0x17, 0xb6, 0x0b, 0x0c, 0xb6, 0x0c, 0x00, 0xb6, 0x0d, 0x00, 0xb6, 0x0e, 0x00, 0xb7, 0x01, 0x39, 0xb7, 0x02, 0x00, 0xb7, 0x03, 0x7f, 0xb7, 0x04, 0x00, 0xb7, 0x05, 0x7f, 0xb7, 0x06, 0x0c, 0xb7, 0x07, 0x00, 0xb7, 0x08, 0x00, 0xb7, 0x09, 0x2b, 0xb7, 0x0a, 0x17, 0xb7, 0x0b, 0x0c, 0xb7, 0x0c, 0x00, 0xb7, 0x0d, 0x00, 0xb7, 0x0e, 0x00, 0xb8, 0x01, 0x5f, 0xb8, 0x02, 0x00, 0xb8, 0x03, 0x7f, 0xb8, 0x04, 0x6b, 0xb8, 0x05, 0x7f, 0xb8, 0x06, 0x1d, 0xb8, 0x07, 0x00, 0xb8, 0x08, 0x00, 0xb8, 0x09, 0x23, 0xb8, 0x0a, 0x17, 0xb8, 0x0b, 0x2a, 0xb8, 0x0c, 0x08, 0xb8, 0x0d, 0x00, 0xb8, 0x0e, 0x66, 0xb9, 0x01, 0x5f, 0xb9, 0x02, 0x00, 0xb9, 0x03, 0x7f, 0xb9, 0x04, 0x6b, 0xb9, 0x05, 0x7f, 0xb9, 0x06, 0x1d, 0xb9, 0x07, 0x00, 0xb9, 0x08, 0x00, 0xb9, 0x09, 0x2c, 0xb9, 0x0a, 0x17, 0xb9, 0x0b, 0x2a, 0xb9, 0x0c, 0x08, 0xb9, 0x0d, 0x00, 0xb9, 0x0e, 0x66, 0xba, 0x01, 0x5f, 0xba, 0x02, 0x00, 0xba, 0x03, 0x7f, 0xba, 0x04, 0x6b, 0xba, 0x05, 0x7f, 0xba, 0x06, 0x1d, 0xba, 0x07, 0x00, 0xba, 0x08, 0x00, 0xba, 0x09, 0x38, 0xba, 0x0a, 0x17, 0xba, 0x0b, 0x2a, 0xba, 0x0c, 0x08, 0xba, 0x0d, 0x00, 0xba, 0x0e, 0x66, 0xbb, 0x01, 0x5f, 0xbb, 0x02, 0x00, 0xbb, 0x03, 0x7f, 0xbb, 0x04, 0x6b, 0xbb, 0x05, 0x7f, 0xbb, 0x06, 0x1d, 0xbb, 0x07, 0x00, 0xbb, 0x08, 0x00, 0xbb, 0x09, 0x4d, 0xbb, 0x0a, 0x17, 0xbb, 0x0b, 0x2a, 0xbb, 0x0c, 0x08, 0xbb, 0x0d, 0x00, 0xbb, 0x0e, 0x66, 0xbc, 0x02, 0x6c, 0xbc, 0x03, 0x02, 0xf7

};

void setup() {
  // put your setup code here, to run once:
  OUT.begin(115200);
}

void loop() {
  // put your main code here, to run repeatedly:

  if (OUT.available() >= 3) {
      // they sent the update request message, send response.
      OUT.read(); 
      OUT.read(); 
      OUT.read(); 
      
      for (int i = 0; i < sizeof(sampleSysexResponse) * sizeof(byte); i++) { 
        OUT.write(sampleSysexResponse[i]);
      }
  }
}
