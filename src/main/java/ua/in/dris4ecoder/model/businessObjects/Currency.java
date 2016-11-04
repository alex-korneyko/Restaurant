package ua.in.dris4ecoder.model.businessObjects;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex Korneyko on 09.09.2016 14:43.
 */
@Entity
@Table(name = "service.currencies")
public class Currency implements BusinessObject {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Integer id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @Column(name = "is_main")
    private boolean isMain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", longName='" + longName + '\'' +
                ", isMain=" + isMain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (shortName != null ? !shortName.equals(currency.shortName) : currency.shortName != null) return false;
        return longName != null ? longName.equals(currency.longName) : currency.longName == null;

    }

    @Override
    public int hashCode() {
        int result = shortName != null ? shortName.hashCode() : 0;
        result = 31 * result + (longName != null ? longName.hashCode() : 0);
        return result;
    }
}
