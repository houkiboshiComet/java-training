package gui.ex22;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JPanel;

public class ClockPanel extends JPanel implements MouseMotionListener {

	private int hour;
	private int minute;
	private int second;
	private Point sun = new Point(200,280);
	private MovePoint earth = new MovePoint(new Point(180,400), new Point2D.Double(20, 0), 35, 0.03);
	private MovePoint moon = new MovePoint(new Point(180,400), new Point2D.Double(0, -10), 35, 0.8);
	private MovePoint venus = new MovePoint(new Point(300,300), new Point2D.Double(0, 10), 25, 0.01);
	private MovePoint mercury = new MovePoint(new Point(280,280), new Point2D.Double(20, -20), 18, 0.02);
	private MovePoint mars  = new MovePoint(new Point(400,400), new Point2D.Double(20, -20), 50, 0.01);
	private MovePoint jupiter  = new MovePoint(new Point(0,100), new Point2D.Double(100, 30), 60, 0.002);
	private MovePoint saturn = new MovePoint(new Point(800,300), new Point2D.Double(-50, 50), 70, 0.001);

	public ClockPanel()  {
		hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
		second = Calendar.getInstance().get(Calendar.SECOND);
		setBackground(Color.BLACK);
		addMouseMotionListener(this);

		new Thread (new Runnable () {
			public void run () {
				while(true) {

					try{
						Thread.sleep (50);
					} catch (InterruptedException e){
					}
					hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					minute = Calendar.getInstance().get(Calendar.MINUTE);
					second = Calendar.getInstance().get(Calendar.SECOND);
					repaint();
				}
			}
		}).start ();

	}
	public Color backColor = new Color(0, 0,40,220);
	public Color fontColor = Color.WHITE;
	public final int FONT_SIZE = 50;
	public Font font = new Font("Arial" ,Font.PLAIN, FONT_SIZE);

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D)  g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		String time = String.format("%02d : %02d : %02d", hour, minute, second);
		Dimension size = getSize();
		g2.setColor(backColor);
		g2.fillRect(0, 0, size.width, size.height);

		g2.setFont(font);

		g2.setColor(fontColor);
		g2.drawString(time, (size.width - g.getFontMetrics().stringWidth(time)) / 2, size.height / 2);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
                );

		/* moon */
		Point p = moon.move(earth.point);
		g2.setColor(new Color(255, 255, 100,200));
		g2.fillOval(p.x - 2, p.y - 2, 4, 4);

		/* earth */
		p = earth.move(sun);
		g2.setColor(new Color(50, 50, 255));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);

		/* sun */
		g2.setColor(new Color(255,100,100,220));
		g2.fillOval(sun.x - 10, sun.y - 10, 20, 20);
		g2.setColor(new Color(255, 255,244,40));


		/* venus */
		p = venus.move(sun);
		g2.setColor(new Color(255,255,100,250));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);

		/* mercury */
		p = mercury.move(sun);
		g2.setColor(new Color(150,250,255,220));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);


		p = mars.move(sun);
		g2.setColor(new Color(220,0,0,220));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);


		p = jupiter.move(sun);
		g2.setColor(new Color(50,255,50,220));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);


		p = saturn.move(sun);
		g2.setColor(new Color(200,100,100,220));
		g2.fillOval(p.x - 5, p.y - 5, 10, 10);

		g2.setColor(new Color(255,255,100,60));
		makeStars(g2);

	}
	private Random random = new Random();
	private int count = 100;
	private Point[] ps = new Point[15];
	void makeStars(Graphics2D g) {
		if(count > 30) {
			count = 0;
			for(int i = 0; i < ps.length; i++) {
				ps[i] = new Point(random.nextInt(getWidth()),random.nextInt(getHeight()));
			}
		} else {
			count++;
		}

		for(int i = 0; i < ps.length; i++) {
			g.drawLine(ps[i].x + 3, ps[i].y, ps[i].x - 3, ps[i].y);
			g.drawLine(ps[i].x , ps[i].y+ 3, ps[i].x, ps[i].y -3);
		}
	}

	@Override
	public void mouseDragged(MouseEvent paramMouseEvent) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void mouseMoved(MouseEvent m) {
		sun.x = m.getX();
		sun.y = m.getY();

	}
}
