package radhika.com.codechallenge.dto;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by User on 4/19/2017.
 */

public class Comment implements KvmSerializable {
    private String name;
    private String comments;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return getName();
            case 1:
                return getComments();

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                setName(value.toString());
                break;
            case 1:
                setComments(value.toString());
                break;

            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index) {
            case 0:
                info.name = "Name";
                info.type = PropertyInfo.STRING_CLASS;
                break;
            case 1:
                info.name = "Comments";
                info.type = PropertyInfo.STRING_CLASS;
                break;

            default:
                break;
        }
    }
}
