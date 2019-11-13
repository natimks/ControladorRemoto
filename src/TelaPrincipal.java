import java.rmi.Naming;
import java.rmi.RemoteException;

import rmi.RemoteAccess;

public class TelaPrincipal {
	public static void main(String[] args) throws RemoteException {
		// System.setSecurityManager(new RMISecurityManager());

		try {
			RemoteAccess obj = (RemoteAccess) Naming.lookup("rmi://10.61.5.89/RemoteControler");
			String token = obj.logIn("12345");
			System.out.println("Token: " + token);
			obj.moveMouse(token, 500, 500);

			byte screenshot[] = obj.getScreenshot(token);
			int altura = obj.getHeightResolution(token);
			int largura = obj.getWidthResolution(token);

			Tela tela = new Tela(screenshot, obj, token);
			double fatorAltura = altura / (double) (tela.getContentPane().getHeight());
			double fatorComprimento = largura / (double) (tela.getContentPane().getWidth());

			tela.setFatorRedimensionamento(fatorAltura, fatorComprimento);
			tela.setVisible(true);
			for (;;) {
				screenshot = obj.getScreenshot(token);
				tela.update(screenshot);
			}

		} catch (Exception e) {
			System.out.println("RemoteControler erro" + e.getMessage());
		}
	}
}
