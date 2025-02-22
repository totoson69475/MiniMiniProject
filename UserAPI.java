package classes;

class UserAPI
{
    void mLine(char vC01, int su){
        for(int i = 0; i < su; i++){
            System.out.print(vC01);
        }
        System.out.println("");
    }

    String mLineReturn(char vC01, int su){
        String result = "";
        for(int i = 0; i < su; i++){
            result += vC01;
        }
        result += "\n";
        return result;
    }
}
