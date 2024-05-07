package ru.alfabank.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InvestorDto {
    public int id;
    public String pin;
    public String accountId;
    public String firstName;
    public String lastName;
    public String secondName;
    public String birthDate;
    public String ucdCode;
    public String ucdSer;
    public String ucdNum;
    public String ucdIssuer;
    public String ucdDate;
    public String address;
    public String snils;
    public String inn;
    public String phone;
    public String email;
    public String publicKey;
    public String publicKeyStatus;
    public String accountNumber;
    public String status;
    public String statusReason;
    public boolean qualified;
    public String qualifiedDate;
    public String qualifiedSource;
    public int techInvestorPersonId;
    public String citizenship;
    public String residentCountry;
    public String qualificationStatementId;
    public Date creationDate;
    public Date updateDate;
    public String region;
    public String onboardingChannel;


}
