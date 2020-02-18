package constants;

public class SQLCommands {
    //  ---  USERS COMMANDS  ---
    public static String SELECT_USER_BY_ID = "SELECT * FROM public.\"User\" WHERE id = ?";
    public static String SELECT_USER_BY_LOGIN = "SELECT * FROM public.\"User\" WHERE login = ?";
    public static String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM public.\"User\" WHERE login = ? AND password = ?";
    public static String SELECT_ALL_USERS = "SELECT * FROM public.\"User\"";
    public static String DELETE_ALL_USERS = "DELETE FROM public.\"User\"";
    public static String INSERT_USER = "INSERT INTO public.\"User\"(login,password,name,surname) VALUES( ?, ?, ?, ?)";
    public static String UPDATE_USER = "UPDATE public.\"User\"login = ?, password = ?,name = ?,surname = ?) WHERE id = ?";
    public static String DELETE_USER = "DELETE FROM public.\"User\" WHERE id = ?";

    //   ---   ADMIN COMMANDS   ---
    public static String SELECT_ADMIN_BY_ID = "SELECT * FROM public.\"Admin\" WHERE \"idUser\" = ?";
    public static String INSERT_NEW_ADMIN = "INSERT INTO public.\"Admin\" VALUES(?, ?)";


    //   ---  COMPANY COMMANDS  ---
    public static String SELECT_COMPANY_BY_ID = "SELECT * FROM public.\"Company\" WHERE id = ?";
    public static String SELECT_COMPANY_BY_USER_ID = "SELECT * FROM public.\"Company\" WHERE \"idUser\" = ?";
    public static String SELECT_ALL_COMPANY = "SELECT * FROM public.\"Company\"";
    public static String DELETE_ALL_COMPANY = "DELETE FROM public.\"Company\"";
    public static String INSERT_COMPANY = "INSERT INTO public.\"Company\" (name, \"idUser\") VALUES(?, ?)";
    public static String UPDATE_COMPANY = "UPDATE public.\"Company\"login = ?, password = ?,name = ?,surname = ?, \"idCompany\" = ?) WHERE id = ?";
    public static String DELETE_COMPANY = "DELETE FROM public.\"Company\" WHERE id = ?";

    //   ---   STATISTICS COMMANDS   ---
    public static String SELECT_STATISTICS_BY_ID = "SELECT * FROM public.\"StatisticRecord\" WHERE \"idCompany\" = ?";
    public static String INSERT_STATISTIC_RECORD = "INSERT INTO public.\"StatisticRecord\"(\"idCompany\",\"periodStart\"," +
            "\"periodEnd\", \"avgShares\", \"profitForHolders\", \"depreciationDeductions\"," +
            " \"beginningMarketYearStockPrice\", \"endingMarketYearStockPrice\", \"dividendValueForPeriod\"," +
            "\"averageMarketSharePrice\") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String DELETE_STATISTIC_RECORD = "DELETE FROM public.\"StatisticRecord\" WHERE \"idCompany\" = ?";


}
