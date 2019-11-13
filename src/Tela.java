import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;

import rmi.RemoteAccess;

public class Tela extends javax.swing.JFrame {
	private javax.swing.JLabel a;
	private static final long serialVersionUID = -1842261777470977698L;
	double fatAltura, fatLargura;

	public Tela(byte image[], RemoteAccess obj, String token) {
		a = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		a.setIcon(new javax.swing.ImageIcon(image));
		add(a);
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				try {
					int x = (int) (e.getX() * fatLargura);
					int y = (int) (e.getY() * fatAltura - getBounds().y);
					obj.moveMouse(token, x, y);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					obj.releaseMouse(token, InputEvent.getMaskForButton(e.getButton()));
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obj.pressMouse(token, InputEvent.getMaskForButton(e.getButton()));
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		pack();
	}

	public void update(byte image[]) {
		a.setIcon(new javax.swing.ImageIcon(image));
	}

	public void setFatorRedimensionamento(double fatAltura, double fatLargura) {
		this.fatAltura = fatAltura;
		this.fatLargura = fatLargura;
	}
}
