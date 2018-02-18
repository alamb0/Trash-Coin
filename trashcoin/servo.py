import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BOARD)

GPIO_SERVO = 3

GPIO.setup(GPIO_SERVO, GPIO.OUT)

pwm = GPIO.PWM(GPIO_SERVO, 50)

pwm.start(0)

def SetAngle(angle):
	duty = angle / 18 + 2
	GPIO.output(GPIO_SERVO, True)
	pwm.ChangeDutyCycle(duty)
	time.sleep(1)
	GPIO.output(GPIO_SERVO, False)
	pwm.ChangeDutyCycle(0)
	
try:
    while True:
        SetAngle(90)
        time.sleep(1)
        SetAngle(0)
        
        # Reset by pressing CTRL + C
except KeyboardInterrupt:
        print("Motor stopped by User")
        pwm.stop()
        GPIO.cleanup()