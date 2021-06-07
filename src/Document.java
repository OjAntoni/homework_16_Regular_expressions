public class Document {
    private final String docNum;
    private final String phone;
    private final String email;

    public Document(String docNum, String email, String phone){
        this.docNum = docNum;
        this.email = email;
        this.phone = phone;
    }

    public String getDocNum() {
        return docNum;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "("+docNum + " " + phone + " " + email+")";
    }
}
