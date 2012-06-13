package server.billete;

import javax.ejb.Remote;

@Remote
public interface BilletefalsoRemote {
public boolean numeroValido(String numero) throws BilletefalsoException;
}
