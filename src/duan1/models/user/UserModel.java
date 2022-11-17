package duan1.models.user;

import duan1.config.*;
import duan1.interfaces.IModel;

import org.bson.Document;
import com.mongodb.client.*;
import org.bson.types.ObjectId;

public class UserModel extends Document implements IModel {
    public String _id = "";
    public String name = "";
    public String email = "";
    public String uname = "";
    public String cccd = "";
    public String joinDate = "";
    public String dateCreated = "";
    public String permission = "";
    public Double salary;
    public String refreshToken = "";
    public String password = "";
    public String avatar = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();
        this.name = document.getString("name");
        this.email = document.getString("email");
        this.uname = document.getString("uname");
        this.cccd = document.getString("cccd");
        this.joinDate = document.getString("joinDate");
        this.dateCreated = document.getString("dateCreated");
        this.permission = document.getString("permission");
        this.salary = document.getDouble("salary");
        this.refreshToken = document.getString("refreshToken");
        this.password = document.getString("password");
        this.avatar = document.getString("avatar");
    }

    @Override
    public Document toDocument() {
        if(!this._id.isEmpty()) put("_id", new ObjectId(this._id));
        if(!this.name.isEmpty()) put("name", this.name);
        if(!this.email.isEmpty()) put("email", this.email);
        if(!this.uname.isEmpty()) put("uname", this.uname);
        if(!this.cccd.isEmpty()) put("cccd", this.cccd);
        if(!this.joinDate.isEmpty()) put("joinDate", this.joinDate);
        if(!this.dateCreated.isEmpty()) put("dateCreated", this.dateCreated);
        if(!this.permission.isEmpty()) put("permission", this.permission);
        if(this.salary != null) put("salary", this.salary);
        if(!this.refreshToken.isEmpty()) put("refreshToken", this.refreshToken);
        if(!this.password.isEmpty()) put("password", this.password);
        if(!this.avatar.isEmpty()) put("avatar", this.avatar);

        return this;
    }
}
