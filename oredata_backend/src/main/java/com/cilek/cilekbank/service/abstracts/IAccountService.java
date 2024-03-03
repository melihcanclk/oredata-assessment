package com.cilek.cilekbank.service.abstracts;
import com.cilek.cilekbank.dto.*;
import java.util.List;

public interface IAccountService {

    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO, String bearerToken);

    GetAccountResponseDTO getAccount(String accountNumber, String bearerToken);

    UpdateAccountResponseDTO updateAccount(String id, UpdateAccountRequestDTO updateAccountRequestDTO, String bearerToken);

    ResponseStatus deleteAccount(String id, String bearerToken);

    List<GetAccountResponseDTO> getAccounts(String bearerToken, String accountName, String accountNumber);

}
