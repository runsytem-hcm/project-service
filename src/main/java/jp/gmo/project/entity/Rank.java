package jp.gmo.project.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class ProjectRank.
 */
@Entity
@Table(name = "rank")
@Data
public class Rank implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /** The rank. */
    @Column(name = "rank")
    private String rank;
    
    /** The description. */
    @Column(name = "description")
    private String description;
    
    /** The create date. */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "create_by")
    private String createBy;
    
    /** The update date. */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_by")
    private String updateBy;

    /** The delete flag. */
    @Column(name = "delete_flag")
    private Integer deleteFlag;
}
