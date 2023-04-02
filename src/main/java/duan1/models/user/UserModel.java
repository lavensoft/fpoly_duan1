package duan1.models.user;

import duan1.config.*;
import duan1.interfaces.IModel;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;

import org.bson.types.ObjectId;

public class UserModel extends IModel {
    public String _id = "";
    public String name = "";
    public String email = "";
    public String uname = "";
    public String cccd = "";
    public String joinDate = "";
    public String dateCreated = "";
    public String permission = "";
    public Double salary = 0.0;
    public String refreshToken = "";
    public String password = "";
    public String avatar = "";

    @Override
    public void fromDocument(Document document) {
        this._id = document.getObjectId("_id").toString();

        if(document.getString("name") != null) this.name = document.getString("name");
        if(document.getString("email") != null) this.email = document.getString("email");
        if(document.getString("uname") != null) this.uname = document.getString("uname");
        if(document.getString("cccd") != null) this.cccd = document.getString("cccd");
        if(document.getString("joinDate") != null) this.joinDate = document.getString("joinDate");
        if(document.getString("dateCreated") != null) this.dateCreated = document.getString("dateCreated");
        if(document.getString("permission") != null) this.permission = document.getString("permission");
        if(document.getDouble("salary") != null) this.salary = document.getDouble("salary");
        if(document.getString("refreshToken") != null) this.refreshToken = document.getString("refreshToken");
        if(document.getString("password") != null) this.password = document.getString("password");
        if(document.getString("avatar") != null) this.avatar = document.getString("avatar");

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

    @Override
    public Bson toUpdates() {
        Bson updates = Updates.combine(
            !this.name.isEmpty() ? Updates.set("name", this.name) : new Document(),
            !this.email.isEmpty() ? Updates.set("email", this.email) : new Document(),
            !this.uname.isEmpty() ? Updates.set("uname", this.uname) : new Document(),
            !this.cccd.isEmpty() ? Updates.set("cccd", this.cccd) : new Document(),
            !this.joinDate.isEmpty() ? Updates.set("joinDate", this.joinDate) : new Document(),
            !this.dateCreated.isEmpty() ? Updates.set("dateCreated", this.dateCreated) : new Document(),
            !this.permission.isEmpty() ? Updates.set("permission", this.permission) : new Document(),
            salary != null ? Updates.set("salary", this.salary) : new Document(),
            !this.refreshToken.isEmpty() ? Updates.set("refreshToken", this.refreshToken) : new Document(),
            !this.password.isEmpty() ? Updates.set("password", this.password) : new Document(),
            !this.avatar.isEmpty() ? Updates.set("avatar", this.avatar) : new Document()
        );

        return updates;
    }
}
