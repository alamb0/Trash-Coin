var EASING = 'easeInOutCubic';
var STAGE = document.querySelector('.stage');
var timeline = anime.timeline({
  loop: false,
  // toggles display of middle and small squares
  update: function update(anim) {
    var CLASS_NAME = 'middle-moving';
    var hasClass = ~STAGE.className.indexOf(CLASS_NAME);
    var currentTime = anim.currentTime;


    if (currentTime < 1500 && !hasClass) {
      STAGE.classList.add(CLASS_NAME);
    } else if (currentTime > 1499 && hasClass) {
      STAGE.classList.remove(CLASS_NAME);
    }
  }
});

timeline
// first movement
.add({
  targets: STAGE,
  duration: 1000,
  easing: EASING,
  offset: 500,
  rotate: -90
}).add({
  targets: '.middle-square',
  easing: EASING,
  offset: 500,
  translateX: ['-50%', '-50%'],
  translateY: ['-50%', '-50%'],
  rotate: {
    value: 180,
    duration: 1000
  }
})
// second movement
.add({
  targets: STAGE,
  duration: 1000,
  easing: EASING,
  offset: 2000,
  rotate: 0
}).add({
  targets: '.circle',
  duration: 1000,
  easing: EASING,
  offset: 2000,
  rotate: function rotate(el, i) {
    var initial = void 0;

    if (i === 0) initial = 180;else if (i === 1) initial = 270;else if (i === 2) initial = 90;else if (i === 3) initial = 0;

    return [initial, initial - 360];
  }
});