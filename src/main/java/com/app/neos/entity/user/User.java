package com.app.neos.entity.user;


import com.app.neos.domain.neos.NeosPointDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.embeddable.user.UserCollegeInfo;
import com.app.neos.embeddable.user.UserLike;
import com.app.neos.embeddable.user.UserMBTI;
import com.app.neos.embeddable.user.UserNeosPower;
import com.app.neos.entity.college.College;
import com.app.neos.entity.follow.Follow;
import com.app.neos.entity.period.Period;
import com.app.neos.type.user.UserCollegeMajor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="TBL_USER")
@Getter @ToString(exclude = "college")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class User extends Period {
    @Id @GeneratedValue
    private Long userId;
    @NotNull
    private String userNickName;
    @NotNull
    private String userOAuthId;
    @NotNull
    private String userOAuthEmail;

    private String userCollegeEmail;
    private String userPhoneNumber;

    @NotNull
    private String userCollegeCertify;

    @Embedded
    private UserCollegeInfo userCollegeInfo;

    @Embedded @NotNull
    private UserNeosPower userNeosPower;
    @NotNull
    private Integer userNeosPoint;
    @NotNull
    private Integer userChattingPoint;

    @Embedded
    private UserLike userLike;

    @Embedded
    private UserMBTI userMBTI;
    @NotNull
    private String userIntroduce;

    private String userFile;

   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="COLLEGE_ID")
   @OnDelete(action = OnDeleteAction.CASCADE)
    private College college;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "followingId",cascade = CascadeType.ALL)
    private List<Follow> follows;

   public void changeCollege(College college){
       this.college =college;
   }
   @Builder
    public User(@NotNull String userNickName, @NotNull String userOAuthId, @NotNull String userOAuthEmail, String userCollegeEmail, String userPhoneNumber, @NotNull String userCollegeCertify, UserCollegeInfo userCollegeInfo, @NotNull UserNeosPower userNeosPower, @NotNull Integer userNeosPoint, @NotNull Integer userChattingPoint, UserLike userLike, UserMBTI userMBTI, @NotNull String userIntroduce, String userFile) {
        this.userNickName = userNickName;
        this.userOAuthId = userOAuthId;
        this.userOAuthEmail = userOAuthEmail;
        this.userCollegeEmail = userCollegeEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userCollegeCertify = userCollegeCertify;
        this.userCollegeInfo = userCollegeInfo;
        this.userNeosPower = userNeosPower;
        this.userNeosPoint = userNeosPoint;
        this.userChattingPoint = userChattingPoint;
        this.userLike = userLike;
        this.userMBTI = userMBTI;
        this.userIntroduce = userIntroduce;
        this.userFile = userFile;
    }

    //    대학교

    public void updateNeosPoint(int point){
        Integer userNeosPoint = this.userNeosPoint;
       if(point < 0){
           userNeosPoint = 0;
       }else{
           userNeosPoint = point;
       }
        this.userNeosPoint = userNeosPoint;
    }

    public void updateNeosPower(int power){
        UserNeosPower userNeosPower = this.userNeosPower;
        if(power < 0){
            userNeosPower.setUserNeosPowerAbility(0);
        }else{
            userNeosPower.setUserNeosPowerAbility(power);
        }
        this.userNeosPower = userNeosPower;
    }

    public boolean levelUpCheck(){
       return this.userNeosPower.getUserNeosPowerAbility() % 100 == 0;
    }

    public void levelUp(){
        int levelUpSection = 0;
        int realLevel = 0;
            levelUpSection = this.userNeosPower.getUserNeosPowerAbility() / 100;
           if(levelUpSection>=0&&levelUpSection<1){
               realLevel=1;
           }else if(levelUpSection>=1&&levelUpSection<2){
               realLevel=2;
           }else if(levelUpSection>=2&&levelUpSection<3){
               realLevel=3;
           }else if(levelUpSection>=3&&levelUpSection<5){
               realLevel=4;
           }else if(levelUpSection>=5&&levelUpSection<7){
               realLevel=5;
           }else if(levelUpSection>=7&&levelUpSection<9){
               realLevel=6;
           }else if(levelUpSection>=9&&levelUpSection<12){
               realLevel=7;
           }else if(levelUpSection>=12&&levelUpSection<14){
               realLevel=8;
           }else if(levelUpSection>=14&&levelUpSection<20){
               realLevel=9;
           }else if(levelUpSection>=20){
               realLevel=10;
           }
            this.userNeosPower.setUserNeosPowerLevel(realLevel);
            this.userNeosPower.setUserNeosBadge("/images/fix/neosLevel"+realLevel+".png");
        }










    public void certifyOk(String certify){
       this.userCollegeCertify = certify;
    }




    public void updateCertify(UserDTO userDTO){
        this.userCollegeCertify = userDTO.getUserCollegeCertify();
    }

    public void updatePower(UserDTO userDTO){
       UserNeosPower userNeosPower = UserNeosPower.builder().userNeosPowerLevel(userDTO.getUserNeosPowerLevel()).userNeosPowerAbility(userDTO.getUserNeosPowerAbility()).userNeosBadge(userDTO
       .getUserNeosBadge()).build();
       this.userNeosPower = userNeosPower;
    }

    public void updatePoint(NeosPointDTO neosPointDTO){
        this.userNeosPoint = this.userNeosPoint + neosPointDTO.getNeosPointMoney();
    }

    public void update(UserDTO userDTO){
        UserCollegeInfo userCollegeInfo = UserCollegeInfo.builder().userCollegeMajor(userDTO.getUserCollegeMajor()).userCollegeYear(userDTO.getUserCollegeYear()).build();
        UserLike userLike = UserLike.builder().userCity(userDTO.getUserCity()).userO2o(userDTO.getUserO2o()).userDay(userDTO.getUserDay()).userTime(userDTO.getUserTime()).
                build();
        UserMBTI userMBTI = UserMBTI.builder().userMbtiColor(userDTO.getUserMbtiColor()).userMbtiName(userDTO.getUserMbtiName()).build();
       this.userNickName = userDTO.getUserNickName();
        this.userCollegeEmail = userDTO.getUserCollegeEmail();
        this.userCollegeInfo = userCollegeInfo;
        this.userChattingPoint = userDTO.getUserChattingPoint();
        this.userLike = userLike;
        this.userFile = userDTO.getUserFile();
        this.userIntroduce = userDTO.getUserIntroduce();
        this.userMBTI = userMBTI;
        this.userCollegeCertify = userDTO.getUserCollegeCertify();
    }



    public UserDTO toDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(this.userId);
        userDTO.setUserNickName(this.userNickName);
        userDTO.setUserOAuthId(this.userOAuthId);
        userDTO.setUserOAuthEmail(this.userOAuthEmail);
        userDTO.setUserCollegeEmail(this.userCollegeEmail);
        userDTO.setUserPhoneNumber(this.userPhoneNumber);
        userDTO.setUserCollegeCertify(this.userCollegeCertify);
        if(this.userCollegeInfo !=null){
            userDTO.setUserCollegeYear(this.userCollegeInfo.getUserCollegeYear());
            userDTO.setUserCollegeMajor(this.userCollegeInfo.getUserCollegeMajor());
        }
        userDTO.setUserNeosBadge(this.userNeosPower.getUserNeosBadge());
        userDTO.setUserNeosPowerLevel(this.userNeosPower.getUserNeosPowerLevel());
        userDTO.setUserNeosPowerAbility(this.userNeosPower.getUserNeosPowerAbility());
        userDTO.setUserNeosPoint(this.userNeosPoint);
        userDTO.setUserChattingPoint(this.userChattingPoint);
        userDTO.setUserIntroduce(this.userIntroduce);
        if(this.userMBTI != null){
            userDTO.setUserMbtiName(this.userMBTI.getUserMbtiName());
            userDTO.setUserMbtiColor(this.userMBTI.getUserMbtiColor());
        }
        userDTO.setUserFile(this.userFile);
        if(this.userLike !=null){
            userDTO.setUserO2o(this.userLike.getUserO2o());
            userDTO.setUserCity(this.userLike.getUserCity());
            userDTO.setUserDay(this.userLike.getUserDay());
            userDTO.setUserTime(this.userLike.getUserTime());
        }
        if(this.college != null){
            userDTO.setCollegeId(this.college.getCollegeId());
            userDTO.setCollegeName(this.college.getCollegeName());
            userDTO.setCollegeCity(this.college.getCollegeCity());
            userDTO.setCollegeEmailDomain(this.college.getCollegeEmailDomain());
            userDTO.setCollegeLogoFile(this.college.getCollegeLogoFile());
        }
        if(this.follows != null){
            List<Long> followList = this.follows.stream().map(i->i.getMyId().getUserId()).collect(Collectors.toList());
            userDTO.setFollowList(followList);
        }
        return userDTO;
    }




}
