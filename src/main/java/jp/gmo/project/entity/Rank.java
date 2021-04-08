package jp.gmo.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rank")
@Getter
@Setter
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rank")
    private String rank;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
