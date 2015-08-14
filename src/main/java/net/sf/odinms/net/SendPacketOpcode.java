package net.sf.odinms.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public enum SendPacketOpcode implements WritableIntValueHolder {
    // GENERAL

    PING, // 0x11
    // LOGIN
    SEND_LINK,//新连接
    LOGIN_STATUS, // 1
    CHOOSE_GENDER,
    LICENSE_RESULT,
    GENDER_SET,
    PIN_OPERATION,
    PIN_ASSIGNED,
    SERVERLIST, // 0xa
    SERVERSTATUS, // 3
    SERVER_IP, // 0xc
    CHARLIST, // 0xb
    CHAR_NAME_RESPONSE, // 0xd
    RELOG_RESPONSE, // 0x16
    ADD_NEW_CHAR_ENTRY, // 0xe
    CHANNEL_SELECTED,
    ALL_CHARLIST,
    // CHANNEL
    CHANGE_CHANNEL, // 0x10
    UPDATE_STATS, // 0x1b
    FAME_RESPONSE,
    UPCHRLOOK,
    ENABLE_TEMPORARY_STATS,
    DISABLE_TEMPORARY_STATS,
    UPDATE_SKILLS, // 0x1e
    CHAR_CASH,
    WARP_TO_MAP, // 0x49
    SERVERMESSAGE, // 0x37
    FAMILY_ACTION,
    OPEN_FAMILY,
    FAMILY_MESSAGE,
    FAMILY_INVITE,
    FAMILY_MESSAGE2,
    FAMILY_SENIOR_MESSAGE,
    FAMILY_GAIN_REP,
    LOAD_FAMILY,
    FAMILY_USE_REQUEST,
    AVATAR_MEGA, // 0x42
    SPAWN_NPC, // 0xb1
    SPAWN_NPC_REQUEST_CONTROLLER, // 0xb3
    REMOVE_NPC,
    SPAWN_MONSTER, // 0x9E
    SPAWN_MONSTER_CONTROL, // 0xA0
    MOVE_MONSTER_RESPONSE, // 0xA3
    GIVE_DF_FROM_OBJECT,
    CHATTEXT, // 0x67
    SHOW_STATUS_INFO, // 0x21
    SHOW_QUEST_COMPLETION, // 0x29
    WHISPER,
    SPAWN_PLAYER, // 0x64
    SHOW_SCROLL_EFFECT, // 0x6B
    SHOW_ITEM_GAIN_INCHAT, // 0x92
    DOJO_WARP_UP,
    ENERGY,
    KILL_MONSTER, // 0x9f
    DROP_ITEM_FROM_MAPOBJECT, // 0xC1
    FACIAL_EXPRESSION, // 0x85
    MOVE_PLAYER, // 0x7E
    MOVE_MONSTER, // 0xA2
    CLOSE_RANGE_ATTACK, // 0x7F
    RANGED_ATTACK, // 0x80
    MAGIC_ATTACK, // 0x81
    OPEN_NPC_SHOP, // 0xe5
    CONFIRM_SHOP_TRANSACTION, // 0xe6
    OPEN_STORAGE, // 0xe8
    OPEN_FRANDELHI,
    MODIFY_INVENTORY_ITEM, // 0x19
    REMOVE_PLAYER_FROM_MAP, // 0x65
    REMOVE_ITEM_FROM_MAP, // 0xC2
    UPDATE_CHAR_LOOK, // 0x88
    CS_LOOK,
    CS_MLS,
    SHOW_FOREIGN_EFFECT, //0x89
    GIVE_FOREIGN_BUFF, //0x8A
    CANCEL_FOREIGN_BUFF, //0x8B
    DAMAGE_PLAYER, // 0x84
    CHAR_INFO, // 0x31
    UPDATE_QUEST_INFO, // 0x97
    GIVE_BUFF, //0x1c
    CANCEL_BUFF, //0x1d
    PLAYER_INTERACTION, // 0xEF
    UPDATE_CHAR_BOX, // 0x69
    NPC_TALK,
    KEYMAP,
    AUTO_HP_POT,
    AUTO_MP_POT,
    SHOW_MONSTER_HP,
    PARTY_OPERATION,
    UPDATE_PARTYMEMBER_HP,
    MULTICHAT,
    APPLY_MONSTER_STATUS,
    CANCEL_MONSTER_STATUS,
    CLOCK,
    SPAWN_PORTAL,
    SPAWN_DOOR,
    REMOVE_DOOR,
    SPAWN_LOVE,
    REMOVE_LOVE,
    SPAWN_SPECIAL_MAPOBJECT,
    REMOVE_SPECIAL_MAPOBJECT,
    SUMMON_ATTACK,
    MOVE_SUMMON,
    SPAWN_MIST,
    REMOVE_MIST,
    DAMAGE_SUMMON,
    DAMAGE_MONSTER,
    BUDDYLIST,
    SHOW_ITEM_EFFECT,
    SHOW_CHAIR,
    SKILL_EFFECT,
    CANCEL_SKILL_EFFECT,
    BOSS_ENV,
    REACTOR_SPAWN,
    REACTOR_HIT,
    REACTOR_DESTROY,
    MAP_EFFECT,
    GUILD_OPERATION,
    ALLIANCE_OPERATION,
    BBS_OPERATION,
    SHOW_MAGNET,
    MESSENGER,
    NPC_ACTION,
    SPAWN_PET,
    MOVE_PET,
    PET_CHAT,
    PET_COMMAND,
    PET_NAMECHANGE,
    COOLDOWN,
    PLAYER_HINT,
    SPAWN_HIRED,
    USE_SKILL_BOOK,
    FORCED_MAP_EQUIP,
    MAP_EFFECTXG,
    SKILL_MACRO,
    CS_OPEN,
    CS_UPDATE,
    CS_OPERATION,
    MTS_OPEN,
    MTS_OPERATION,
    MTS_OPERATION2,
    PLAYER_NPC,
    SHOW_NOTES,
    SUMMON_SKILL,
    ARIANT_PQ_START,
    CATCH_MONSTER,
    ARIANT_SCOREBOARD,
    ZAKUM_SHRINE,
    BOAT_EFFECT,
    CHALKBOARD,
    DUEY,
    MONSTER_CARNIVAL_START,
    MONSTER_CARNIVAL_OBTAINED_CP,
    MONSTER_CARNIVAL_PARTY_CP,
    MONSTER_CARNIVAL_SUMMON,
    MONSTER_CARNIVAL_DIED,
    SEND_TV,
    REMOVE_TV,
    ENABLE_TV,
    TROCK_LOCATIONS, //0x27
    SPOUSE_CHAT, // 0x66
    REPORT_PLAYER_MSG,
    SPAWN_HIRED_MERCHANT,
    DESTROY_HIRED_MERCHANT,
    UPDATE_HIRED_MERCHANT,
    GM_POLICE,
    UPDATE_MOUNT,
    MONSTERBOOK_ADD,
    MONSTER_BOOK_CHANGE_COVER,
    CYGNUS_INTRO_LOCK,
    CYGNUS_INTRO_DISABLE_UI,
    CYGNUS_CHAR_CREATED,
    TUTORIAL_DISABLE_UI,
    TUTORIAL_LOCK_UI,
    TUTORIAL_SUMMON,
    TUTORIAL_GUIDE,
    SHOW_INFO,
    COMBO_EFFECE,
    Animation_EFFECT,
    VICIOUS_HAMMER,
    BLOCK_MSG,
    FAMILY,
    CHANGE_JOB,
    DragonBall1,
    MonsterBombSkill,
    EVERY_DAY_SALE, //商城 每日特卖
    SPAWN_PORTAL2, //机械师传送门的传送点
    SPAWN_DOOR2, //机械师传送门的效果
    REMOVE_DOOR2, //取消机械师传送门        
    SPAWN_MAGNETIC_FIELD, //召唤机械师的磁场
    SHOW_Orientation,//倾向系统
    CS_INTO, //进商城除了CS_OPEN会多发一个这个包 包括帐号和商城内的点装信息
    Maple_Tip1, //PVP提示1[屏幕正中间黄字]
    Maple_Tip2, //PVP提示2[屏幕正中间绿字]
    Maple_Tip3, //PVP提示3[对话框内多种颜色的字]
    Ultimate,//创建终极冒险家
    BEANS_GAME1, //出现打豆豆的界面
    BEANS_GAME2, //豆豆出现
    BEANS_TIPS, //豆豆机上方出现中奖公告
    UPDATE_BEANS, //更新豆豆数量
    NECKLACE_EXPANSION,
    //龙神
    DRAGON_MOVE,
    DRAGON_SPAWN,
    //DRAGON_REMOVE,
    MAGNIFLER_SCROLL,
    MAGIC_SCROLL,
    CANCEL_CHAIR,
    ANDROID_SPAWN,
    ANDROID_MOVE,
    ANDROID_EMOTION,
    ANDROID_UPDATE,
    ANDROID_DEACTIVATED,
    MARRIED_EFFECT,
    YELLOW_TIP,
    MUSICBOX,
    CHANGE_CMD,
    ATTACKEFFECT,
    GAME_PROPERTY_CHANGE,
    MAGIC_RESULT;

    private SendPacketOpcode() {
    }
    private int code = -2;

    public void setValue(int code) {
        this.code = code;
    }

    @Override
    public int getValue() {
        return code;
    }

    public static Properties getDefaultProperties() throws FileNotFoundException, IOException {
        // ResourceBundle resb1 = ResourceBundle.getBundle("sendops", Locale.SIMPLIFIED_CHINESE);

        Properties props = new Properties();
        props.load(Class.class.getResourceAsStream("/sendops.properties"));
        System.out.println(props.getProperty("VERSION"));
        /*     for (String key : resb1.keySet()) {
         props.put(key, resb1.getString(key));
         }*/
        return props;
    }

    static {
        try {
            ExternalCodeTableGetter.服务端包头获取(getDefaultProperties(), values());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sendops", e);
        }
    }
}
