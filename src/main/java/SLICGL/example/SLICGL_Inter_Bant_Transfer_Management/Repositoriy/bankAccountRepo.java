package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.balanceEnterDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.bankAccountListForManualTransfersDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBankAccountListDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBankAccountsForFundRequestDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bankAccountRepo extends JpaRepository<bankAccount, String> {

    @Query(value = "SELECT account_id FROM bank_account ORDER BY registered_date DESC LIMIT 1", nativeQuery = true)
    public String getLastAccountId();

    @Modifying
    @Query(value = "UPDATE bank_account SET bank = ?1, bank_branch = ?2, account_number = ?3, account_type = ?4, currency = ?5, gl_code = ?6 WHERE account_id = ?7", nativeQuery = true)
    public int updateAccount(String bankName, String bankBranch, String accountNumber, int accountType, String currency, int glCode, String accountId);

    @Modifying
    @Query(value = "UPDATE bank_account SET delete_status = 1, deleted_by = ?1 WHERE account_id = ?2", nativeQuery = true)
    public int deleteAccount(String deletedUser, String accountId);

    @Query(value = "SELECT ACC.account_id, BNK.bank_name, ACC.bank_branch, ACC.account_number FROM bank_account ACC LEFT JOIN bank BNK ON ACC.bank = BNK.bank_id WHERE delete_status = 0 ORDER BY bank_name DESC", nativeQuery = true)
    public List<balanceEnterDTO> getAllBankAccounts();

    @Query(value = "SELECT account_id, account_number FROM bank_account WHERE delete_status = 0", nativeQuery = true)
    public List<getBankAccountsForFundRequestDTO> getBankAccountsForFundRequest();

    @Query(value = "SELECT account_id, account_number FROM bank_account WHERE delete_status = 0", nativeQuery = true)
    public List<getBankAccountListDTO> accountList();

    @Query(value = "SELECT CASE WHEN (SELECT acc.account_id FROM bank_account acc WHERE acc.account_id = ? AND acc.delete_status = '0') IS NULL THEN false ELSE true END AS 'availability'", nativeQuery = true)
    public Integer accountAvailability(String accountId);

    @Query(value = "SELECT acc.account_id, acc.account_number FROM bank_account acc LEFT JOIN account_balance bal ON \n" +
            "acc.account_id = bal.account_id WHERE acc.delete_status = 0 AND bal.delete_status = 0 AND DATE(bal.balance_date)\n" +
            "= CURRENT_DATE", nativeQuery = true)
    public List<bankAccountListForManualTransfersDTO> getAccountList();
}
