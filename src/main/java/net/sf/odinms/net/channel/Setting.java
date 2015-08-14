package net.sf.odinms.net.channel;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import net.sf.odinms.tools.HexTool;

public class Setting {

    private static Setting instance = null;
    private static final Properties itempb_cfg = new Properties();
    private static ArrayList<Integer> banitems = new ArrayList<Integer>();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Setting.class);
    private static byte[] cashshoppacket, wzdata_;
    private static String wzdata = "24 00 8C 0C 00 00 8B 44 24 04 60 9C 50 E8 09 00 00 00 89 44 24 20 9D 61 C2 04 00 90 E8 00 00 00 00 60 BE CC CC D8 D5 BF 00 00 00 00 BE 00 00 00 00 E8 0E 00 00 00 00 00 00 00 00 00 58 BF 00 00 00 00 BD 00 59 BE 00 BF 00 00 B8 00 83 C0 68 BD 00 00 00 00 83 C1 70 B8 00 BF 00 00 BD 00 B9 87 C9 B8 B8 BE 00 00 BA 9D E7 84 B8 BF 00 BE 00 00 BE 00 BA 9E 02 BF 00 BB 00 00 BB DE 02 00 00 BD 00 BB 00 00 B8 00 31 08 BF BD 00 00 00 BD 31 11 B8 00 BF 00 00 BD 00 83 C0 04 B8 75 E9 61 6F 83 C1 04 4B 75 E9 61 FD 59 48 74 9D BF 3F 93 5E 64 44 06 1E 0C 81 7E 75 E1 84 B8 9D 77 D1 EE CA 83 25 E0 23 E7 84 DE 11 5C 00 55 E9 EC 3A F8 A9 6C 04 3B 5D 8F 3F 53 97 6C C4 06 16 97 98 15 26 A7 8C 33 6D 5E 49 61 CE 5F 39 C0 9D E7 84 07 43 6C 53 33 20 9F 87 6B 16 5D 3A BA 9D E7 3A B8 9D E7 C8 03 FC 83 C8 D1 23 95 E5 CA E4 D6 8C 03 FD E7 84 B8 23 E8 84 B8 9D 5C E1 CC CD 95 07 78 99 AD F1 51 FC 4A E6 27 E9 F2 88 54 25 2A 5D EB 9E 2A 5D EB 25 C2 5B EB 25 2A 5D EB 25 2A 5D EB 7D 95 5D EB 25 2A E0 EB 25 2A 5D 54 25 2A 5D EB A6 EA 35 54 25 2A 5D EB 98 2A 5D EB 25 95 5D EB 25 2A E4 6C EC 08 E5 55 25 2A 5D EB 9E 2A 5D EB 25 94 5D EB 25 2A E7 75 27 2A 5D 50 25 2A 5D EB 9B 2A 5D EB 25 91 5D EB 25 2A 6C E3 9A 2A 5D EB 25 97 5D EB 25 2A E2 EB 25 2A 5D 68 E5 2E 17 9E CC 4B 32 22 07 92 DA 7A 5C B9 19 A1 C7 82 59 C9 02 54 D9 CB 8E D1 DB B2 52 C4 8D 46 A6 A2 DA 22 07 F4 56 EB 83 7F AE 29 8C D2 EE A9 87 2A DA 22 07 79 D0 A9 47 9E 51 52 1B 3F 51 62 0F 19 2A A9 79 AE D9 DC BF EA DA 22 07 19 04 A9 D0 19 88 5A 04 41 51 48 27 91 31 CA 0A 92 DA 22 4B FD BB 46 4B FB B8 50 66 E0 A3 63 07 7A BA 22 07 92 32 2D 07 92 DA 65 62 E6 8A 50 68 F1 9B 46 63 E0 BF 51 74 92 32 65 07 92 DA CA 16 92 DA 22 40 F7 AE 6F 68 F6 AF 4E 62 DA BB 4C 63 FE BF 63 07 7A F6 22 07 92 32 3E 07 92 DA 71 62 E6 8F 4C 6F F3 B4 46 6B F7 BE 67 7F F1 BF 52 73 FB B5 4C 41 FB B6 56 62 E0 DA CA 01 92 DA 22 5F CB 81 78 EC C0 E9 E2 BE 6D 25 22 07 19 A6 06 03 60 74 09 7B B6 DE A9 C8 DB E9 E2 52 C3 51 2E 23 19 AE 06 0B 19 A7 22 04 69 29 84 73 9F 59 E7 03 D2 E1 60 1F E7 3D 11 C7 79 CF A9 6D B6 D9 C9 08 25 96 67 07 19 B0 3E 04 79 51 66 8A 92 D9 E1 5E CF 53 66 23 96 19 B2 58 CC 87 CB 22 97 DA 22 EF 99 DA 22 07 C1 9E 66 7E FC 9E 4E 6B A2 ED 22 EF 92 DA 22 07 13 DE 06 C1 91 DA 22 EF 92 DA 22 07 13 DE 06 FD 9A DA 22 EF 92 DA 22 07 13 DE 06 7E 95 DA 22 84 56 DE DD 53 B6 26 A1 C3 9E 33 2F 03 92 DA 70 57 7A DA 22 07 92 5B 26 23 73 DE 22 07 CA 59 E6 03 1D DA A1 EB 9A 82 A1 C3 96 33 A6 02 92 DA CA 0C 92 DA 22 54 D6 9E 5B 69 D6 B6 4E 37 A6 DA CA 07 92 DA 22 86 96 FE 41 04 92 DA CA 07 92 DA 22 86 96 FE 78 02 92 DA CA 07 92 DA 22 86 96 FE 34 00 92 DA A1 C3 96 25 76 23 6E 59 E6 0B 7B BB 21 07 92 DA 22 07 92 33 0D 02 92 DA EE 07 92 DA 22 EE B9 DA 22 07 FA 7A 66 E8 A7 8B 74 57 7A DA 22 07 92 5B 26 23 78 25 DD F8 CA 51 22 80 96 FE A1 C3 96 25 76 23 6E 33 3C 06 92 DA CB 0F 9A DA 22 07 92 DA 22 EE CD DA 22 07 C2 32 22 07 92 DA A3 03 B6 2B DD F8 6D 82 A9 07 15 DE 06 5F 7B 9B 23 07 92 32 22 07 92 DA A3 03 B6 76 23 07 92 84 CB 3B 90 DA 22 54 C2 32 22 07 92 DA A3 03 B6 C5 20 07 92 82 A1 C3 96 55 22 84 7E D2 7A 84 56 DE CB 5C 91 DA 22 0E 52 33 6F 01 92 DA EE 8C 0E FE 0A 07 92 DA CB 06 96 DA 22 07 92 DA 22 EE F0 DB 22 07 19 66 06 03 92 DA 22 EE 7D DE 22 07 7A CB 22 07 92 8E 47 75 FF B3 4C 66 E6 BF 72 75 FD B9 47 74 E1 DA 74 EF 92 DA 22 07 13 DE 06 68 90 DA 22 EF 92 DA 22 07 13 DE 06 1A 94 DA 22 84 56 DE DD 53 B6 26 A1 C3 9E 33 66 06 92 DA 4A 07 92 DA 22 EF 97 DA 22 07 5E 3B E8 B9 92 32 3D 07 92 DA 71 43 D6 A3 4C 37 A0 F4 46 6B FE 14 E6 BB 6C 68 99 B3 74 0E F8 2B 55 31 9A FB 42 18 81 A6 92 B2 22 07 92 DA 72 EF 92 DA 22 07 13 DE 06 CE 6C 25 DD 5F 19 DA A5 03 B6 59 E6 03 6D 8E 06 FB 7B 56 21 07 92 51 DC EE 89 DC 22 07 F3 33 BA 07 92 DA CB 06 92 DA 22 CB 7B 61 21 07 92 32 2E 07 92 DA 64 75 F7 BF 6E 6E F0 A8 43 75 EB DA 74 EF 92 DA 22 07 13 DE 06 E1 94 DA 22 EF 92 DA 22 07 13 DE 06 72 97 DA 22 84 56 DE DD 53 B6 26 A1 C3 9E 33 34 07 92 DA A3 FF 93 DA 22 07 7B 31 27 07 92 D5 A6 F7 97 DA 22 EE 02 DF 22 07 7A D6 22 07 92 99 4E 68 E1 BF 6A 66 FC BE 4E 62 92 8C CA 07 92 DA 22 86 96 FE 17 07 92 DA CA 07 92 DA 22 86 96 FE 04 02 92 DA A1 C3 96 25 76 23 6E 59 E6 0B 7B 11 DC F8 6D 33 AA 02 92 DA E0 0F 92 33 01 06 92 DA CB 8B 90 DA 22 CB 92 DA 22 07 7B E0 23 07 92 33 56 07 92 DA 49 62 E0 B4 47 6B A1 E8 0C 63 FE B6 22 EE 80 DB 22 07 9D 5E 98 02 92 DA CB 7E 92 DA 22 EE 53 DA 22 07 7B C4 23 07 92 33 F2 06 92 DA CA 0B 92 DA 22 4A F7 A9 51 66 F5 BF 60 68 EA 9B 22 EF 99 DA 22 07 E7 A9 47 75 A1 E8 0C 63 FE B6 22 EF 92 DA 22 07 13 DE 06 9A 6F 25 DD EF 92 DA 22 07 13 DE 06 96 96 DA 22 84 56 DE DD 53 B6 26 A1 C3 9E 33 1A FA 6D 25 CA 07 92 DA 22 86 96 FE 44 F8 6D 25 7A EE 73 D8 22 07 5E DA 22 07 92 33 32 04 92 DA 8E EE 88 DE 22 07 A1 1A CB D5 90 DA 22 EF 9F DA 22 07 DE B5 43 63 DE B3 40 75 F3 A8 5B 46 92 8C CA 07 92 DA 22 86 96 FE 9D 06 92 DA CA 07 92 DA 22 86 96 FE 0C 03 92 DA A1 C3 96 25 76 23 6E 59 E6 0B 7B 5A DC F8 6D 51 96 23 B6 DA 22 07 7B D1 27 07 92 BB CB E1 91 DA 22 F8 26 FE 2A 07 92 DA 72 EF 92 DA 22 07 13 DE 06 79 93 DA 22 5F 19 DA A5 03 B6 59 E6 03 6D 8E 06 FB 7B 7C 20 07 92 33 51 F8 6D 25 EE EE E8 D8 22 07 7A AE 22 07 92 33 71 05 92 DA 71 63 F6 A3 4C 37 A0 F4 46 6B FE DA CB 13 90 DA 22 07 92 DA 22 EE 23 26 DD F8 17 1A CB 5B 93 DA 22 C0 54 DA 22 50 81 33 7E 06 92 DA CA 0C 92 DA 22 54 D6 9E 5B 69 D6 B6 4E 37 A4 DA CA 07 92 DA 22 86 96 FE 9D F8 6D 25 CA 07 92 DA 22 86 96 FE 4D 04 92 DA CA 07 92 DA 22 86 96 FE 50 04 92 DA A1 C3 96 25 76 23 6E 59 E6 0B 7B 6C D9 F8 6D 33 9C 07 92 DA EE EF 99 DA 22 07 C1 9E 66 7E FC 9E 4E 6B A2 E2 22 EF 92 DA 22 07 13 DE 06 71 6D 25 DD EF 92 DA 22 07 13 DE 06 38 6E 25 DD EF 92 DA 22 07 13 DE 06 2E 91 DA 22 84 56 DE DD 53 B6 26 A1 C3 9E 33 32 F9 6D 25 73 57 7A DA 22 07 92 5B 26 23 2F DB 22 07 CA 59 E6 03 1D DA A1 EB 9A 82 A1 C3 96 33 B2 FC 6D 25 CB 95 6D 25 DD EF 92 DA 22 07 13 DE 06 44 93 DA 22 EF 92 DA 22 07 13 DE 06 A2 90 DA 22 EF 92 DA 22 07 13 DE 06 D1 92 DA 22 EF 92 DA 22 07 13 DE 06 21 92 DA 22 57 7A DA 22 07 92 5B 26 23 3B D8 22 07 CA 51 22 80 96 FE A1 C3 96 25 76 23 6E 33 01 05 92 DA 22 EE 25 DA 22 07 F2 33 27 FB 6D 25 E1 EE 5B D8 22 07 13 22 22 07 92 DA CB 09 91 DA 22 CB 02 33 70 04 92 DA 22 07 92 DA CB D0 91 DA 22 57 7A DA 22 07 92 5B 26 23 CF D8 22 07 CA 51 22 80 96 FE 7A EE 3A 24 DD F8 13 21 32 35 C6 AC CB FD 6E 25 DD 08 17 44 DC F8 6D 33 1E FB 6D 25 72 EF 92 DA 22 07 13 DE 06 72 92 DA 22 5F 19 DA A5 03 B6 59 E6 03 6D 8E 06 FB 7B 8B 22 07 92 B2 22 07 92 DA 4A F8 6D 25 DD 57 7A DA 22 07 92 5B 26 23 C8 24 DD F8 CA 51 22 80 96 FE A1 C3 96 25 76 23 6E 33 0B FA 6D 25 4F 74 CD EB 1B 33 A5 E9 16 30 A7 ED 1A 31 AB EE 15 37 92 33 89 06 92 DA EE EE 97 DA 22 07 7B 9D 21 07 92 33 67 07 92 DA A3 FF EA DB 22 07 7B 58 22 07 92 DA 22 07 92 33 92 06 92 DA 72 EF 92 DA 22 07 13 DE 06 F0 68 25 DD 5F 19 DA A5 03 B6 82 CB 1E 6D 25 DD 6A E1 85 10 32 A5 E3 1A 30 A4 ED 17 34 A4 E2 10 33 A3 DA CB 94 6D 25 DD EE 3D 27 DD F8 5E 18 26 07 7B B9 20 07 92 18 26 07 7B BF 22 07 92 5B DA 07 92 DA 22 EE 01 DB 22 07 7A DA 22 07 92 5B 26 23 26 DA 22 07 CA 33 FD 06 92 DA CB 4F 92 DA 22 8E 95 33 9E F9 6D 25 22 07 92 DA CB CC 6C 25 DD 08 16 88 DC F8 6D 33 10 07 92 DA EE F8 26 FE 2E 07 92 DA 72 57 7A DA 22 07 92 5B 26 23 56 26 DD F8 CA 51 22 80 96 FE A1 C3 96 25 76 23 6E 33 E0 FD 6D 25 CB B0 92 DA 22 EE 56 DB 22 07 FA DA 22 07 92 32 27 07 92 DA EE E6 58 64 22 EF 8F DA 22 07 C1 9E 66 7E FC EA 10 29 F6 B6 4E B7 74 6B 9C CB 39 6F EF 2B 55 31 9A FB 42 18 81 A6 92 B2 22 07 92 DA 72 EF 92 DA 22 07 13 DE 06 CA 6B 25 DD 5F 19 DA A5 03 B6 59 E6 03 6D 8E 06 FB 7B DF 22 07 92 33 27 05 92 DA 4A 07 92 DA 22 6F 6D 25 DD F8 C2 32 22 07 92 DA A3 03 B6 3F DE F8 6D 82 A9 07 15 DE 06 84 56 DE DD 53 B6 26 CB 07 92 DA 22 EE E7 26 DD F8 7B 54 DF F8 6D B2 23 07 92 DA 72 EF 92 DA 22 07 13 DE 06 A8 6B 25 DD 5F 11 1E 26 88 92 59 CE 0F CA 59 E6 03 7B 96 DE F8 6D 16 CA 07 92 DA 22 86 96 FE 7F 06 92 DA 7A EE 14 DA 22 07 38 33 05 06 92 DA 10 C5 7B 29 DD F8 6D B7 51 58 A2 E2 12 33 A5 E8 1A 36 AB E9 15 34 AB E9 15 07 7B 5B DF F8 6D 19 CB 13 92 DA 22 08 17 00 DC F8 6D 33 2B FB 6D 25 22 07 92 DA CB 7C 93 DA 22 EE 93 DA 22 07 5E 25 96 23 9A DA 22 07 C2 32 22 07 92 DA A3 03 B6 7E DC F8 6D 82 A9 07 15 DE 06 84 56 DE DD 53 B6 26 CB 6B 6B 25 DD 08 16 1B DC F8 6D 33 22 07 92 DA A3 FF 93 DA 22 07 7B 5E 22 07 92 33 8D FD 6D 25 E1 EE 3B DA 22 07 FA 3A 77 26 63 B2 E2 6A B6 D2 71 51 C2 32 22 07 92 DA A3 03 B6 C6 23 07 92 82 A9 07 15 DE 06 84 56 DE DD 53 B6 26 CB D9 6B 25 DD C0 50 7A 66 E8 A7 33 15 07 92 DA 42 EE 56 22 DD F8 5E 18 26 07 7B 76 D8 F8 6D D5 A7 65 69 25 DD EE 5B 22 DD F8 9D 5F 27 07 92 DA CB 01 92 DA 22 66 7B 4C 22 07 92 51 96 23 B6 DA 22 07 7B 3C DA F8 6D 5F F9 EE 83 DA 22 07 7B 53 D4 F8 6D D5 A6 41 68 25 DD EE 12 DA 22 07 5E D5 A6 D7 6D 25 DD EE 58 20 DD F8 19 5E 06 03 92 DA 22 EE DA 21 DD F8 7B 71 DF F8 6D 1B E8 02 7B D3 22 07 92 DA 22 07 92 33 30 07 92 DA 69 EE A6 DA 22 07 19 56 06 2F 92 DA 22 EE 9A 22 DD F8 C4 B9 63 74 FF FA 66 7E FC 99 4D 63 F7 FA 74 62 E0 EB 0C 34 B2 93 4C 61 FD E0 76 62 E1 AE 7D 34 A4 DA CB DD 6B 25 DD CB 9D 5F 59 F8 6D 25 CB 63 6D 25 DD C5 9A DA CB F4 65 25 DD EE 93 24 DD F8 13 22 20 07 92 DA CB CA 6B 25 DD EE 92 DA 22 07 7B DA 22 07 92 33 DA FF 6D 25 22 07 92 DA CB 92 6B 25 DD CB F2 33 51 FD 6D 25 22 07 92 DA CB 8B 65 25 DD C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 19 E1 C4 51 5D EB 25 E7 84 B8 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 8D 01 00 10 00 01 00 00 00 00 00 00 00 00 00";

    static {
        getInstance().ReadData();
    }

    public void ReadData() {
        try {
            InputStreamReader is = new FileReader("其他设置.properties");
            itempb_cfg.load(is);
            is.close();
            banitems.clear();
            for (String str : itempb_cfg.getProperty("cashban").split(",")) {
                if (!banitems.contains(Integer.parseInt(str))) {
                    banitems.add(Integer.parseInt(str));
                } else {
                    log.warn("发现商城重复封物品编号：" + str);
                }
            }
            banitems.trimToSize();
            if (cashshoppacket == null) {
                Setting.cashshoppacket = HexTool.getByteArrayFromHexString(itempb_cfg.getProperty("cashshoppacket"));
            }
            wzdata_ = HexTool.getByteArrayFromHexString(wzdata);
        } catch (Exception e) {
            log.error("Could not configuration：", e);
        }
    }

    public static byte[] getcashpacket() {
        if (log.isDebugEnabled()) {
            getInstance().ReadData();
        }
        return cashshoppacket;
    }

    public boolean IsBanedItemId(int id) {
        if (log.isDebugEnabled()) {
            getInstance().ReadData();
        }
        return banitems.contains(id);
    }

    public static Setting getInstance() {
        if (instance == null) {
            instance = new Setting();
        }
        return instance;
    }

    public static byte[] getWzdata_() {
        return wzdata_;
    }
}
