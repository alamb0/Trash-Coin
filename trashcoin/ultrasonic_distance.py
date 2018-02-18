#Libraries
import RPi.GPIO as GPIO
import time
import requests
 
# define the api-endpoint 
API_ENDPOINT = "http://8792699f.ngrok.io/near/user?x=2.5&y=5.4"
 
#GPIO Mode (BOARD / BCM)
GPIO.setmode(GPIO.BCM)
 
#set GPIO Pins
GPIO_TRIGGER = 18
GPIO_ECHO = 24
GPIO_SERVO = 2
 
#set GPIO direction (IN / OUT)
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)
GPIO.setup(GPIO_SERVO, GPIO.OUT)
 
#set up PWM for Servo Motor
pwm = GPIO.PWM(GPIO_SERVO, 50)
pwm.start(0)

motor_running = False

def SetAngle(angle):
	duty = angle / 18 + 2
	GPIO.output(GPIO_SERVO, True)
	pwm.ChangeDutyCycle(duty)
	time.sleep(1)
	GPIO.output(GPIO_SERVO, False)
	pwm.ChangeDutyCycle(0)

def distance():
    # set Trigger to HIGH
    GPIO.output(GPIO_TRIGGER, True)
 
    # set Trigger after 0.01ms to LOW
    time.sleep(0.00001)
    GPIO.output(GPIO_TRIGGER, False)
 
    StartTime = time.time()
    StopTime = time.time()
 
    # save StartTime
    while GPIO.input(GPIO_ECHO) == 0:
        StartTime = time.time()
 
    # save time of arrival
    while GPIO.input(GPIO_ECHO) == 1:
        StopTime = time.time()
 
    # time difference between start and arrival
    TimeElapsed = StopTime - StartTime
    # multiply with the sonic speed (34300 cm/s)
    # and divide by 2, because there and back
    distance = (TimeElapsed * 34300) / 2
 
    return distance
 
if __name__ == '__main__':
    try:
        while True:
            dist = distance()
            print ("Measured Distance = %.1f cm" % dist)
            if dist < 10:
                
                motor_running = True
            
                # data to be sent to api
                data = {}
                 
                # sending post request and saving response as response object
                r = requests.post(url = API_ENDPOINT, data = data)
                
                # while motor_running:
                    # turn servo motor to dispense trash

                SetAngle(90)
                time.sleep(1)
                SetAngle(0)
                time.sleep(1)
                print("Trash Emptied")
                #motor_running = False
                    
                # print text from url 
                url_text = r.text
                print("The URL is:%s" % url_text)
            
            time.sleep(1)

            #motor_running = True
 
        # Reset by pressing CTRL + C
    except KeyboardInterrupt:
        print("Measurement stopped by User")
        pwm.stop()
        GPIO.cleanup()
