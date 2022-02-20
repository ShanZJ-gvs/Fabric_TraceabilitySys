document.addEventListener("DOMContentLoaded", () => {
  // Sortable
  let element = null;

  element = document.getElementById("sortable-style-1");
  if (element) {
    const sortable = Sortable.create(element, {
      animation: 150,
    });
  }

  element = document.getElementById("sortable-style-2");
  if (element) {
    const sortable = Sortable.create(element, {
      handle: ".handle",
      animation: 150,
    });
  }

  element = document.getElementById("sortable-style-3");
  if (element) {
    const sortable = Sortable.create(element, {
      animation: 150,
    });
  }

  // Editors
  // CKEditor
  const editor = document.getElementById("ckeditor");
  if (editor) {
    ClassicEditor.create(editor);
  }

  // Carousel
  const carousel = document.getElementById("carousel-style-1");
  if (carousel) {
    new Glide(carousel, {
      type: "carousel",
      perView: 4,
      gap: 20,
      breakpoints: {
        640: {
          perView: 1,
        },
        768: {
          perView: 2,
        },
      },
    }).mount();
  }
});

// Event delegation
const on = (selector, eventType, childSelector, eventHandler) => {
  const elements = document.querySelectorAll(selector);
  for (element of elements) {
    element.addEventListener(eventType, (eventOnElement) => {
      if (eventOnElement.target.closest(childSelector)) {
        eventHandler(eventOnElement);
      }
    });
  }
};

// Animate CSS
const animateCSS = (node, animationName, callback) => {
  node.classList.add("animated", "faster", animationName);

  const handleAnimationEnd = () => {
    node.classList.remove("animated", "faster", animationName);
    node.removeEventListener("animationend", handleAnimationEnd);
    if (typeof callback === "function") callback();
  };

  node.addEventListener("animationend", handleAnimationEnd);
};

// Animate Node
const animateNode = (node, callback) => {
  const handleTransitionEnd = () => {
    node.removeEventListener("transitionend", handleTransitionEnd);
    if (typeof callback === "function") callback();
  };

  node.addEventListener("transitionend", handleTransitionEnd);
};

// Toggle Card Selection
const toggleCardSelection = (event) => {
  const card = event.target.closest(".card");
  card.classList.toggle("card_selected");
};

on("body", "click", '[data-toggle="cardSelection"]', (event) => {
  toggleCardSelection(event);
});

// Toggle Row Selection
const toggleRowSelection = (event) => {
  const row = event.target.closest("tr");
  row.classList.toggle("row_selected");
};

on("body", "click", '[data-toggle="rowSelection"]', (event) => {
  toggleRowSelection(event);
});

// Viewport Width
// Define our viewportWidth variable
let viewportWidth;

// Set/update the viewportWidth value
const setViewportWidth = () => {
  viewportWidth = window.innerWidth || document.documentElement.clientWidth;
};

// Watch the viewport width
const watchWidth = () => {
  const sm = 640;
  const md = 768;
  const lg = 1024;
  const xl = 1280;

  const menu = document.querySelector(".menu-bar");

  if (viewportWidth < sm) {
    if (!menu.classList.contains("open")) {
      menu.classList.remove("icon-only");
      menu.classList.add("hidden");
      document.body.classList.add("menu-hidden");
    }
  } else if (viewportWidth < md) {
    menu.classList.remove("hidden");
    document.body.classList.remove("menu-hidden");
  } else if (viewportWidth < lg) {
  } else if (viewportWidth < xl) {
  } else {
  }
};

// Set our initial width
setViewportWidth();
watchWidth();

// On resize events, recalculate
window.addEventListener(
  "resize",
  () => {
    setViewportWidth();
    watchWidth();
  },
  false
);

// Show active page
const showActivePage = () => {
  const pageUrl = window.location.href.split(/[?#]/)[0];

  const pageLinkSelector = ".menu a";

  const pageLinks = document.querySelectorAll(pageLinkSelector);

  if (!pageLinks) return;

  pageLinks.forEach((pageLink) => {
    if (pageLink == pageUrl) {
      pageLink.classList.add("active");
      const mainMenuTrigger = pageLink.parentNode.dataset.menu;

      if (!mainMenuTrigger) return;

      const mainMenu = document.querySelector(
        '.menu-items a[data-target="' + mainMenuTrigger + '"]'
      );
      mainMenu.classList.add("active");
    }
  });
};

showActivePage();

// Alerts
const alerts = () => {
  // Close
  const closeAlert = (alert) => {
    animateCSS(alert, "fadeOut", () => {
      alert.style.transition = "all .2s linear";
      alert.style.opacity = 0;
      alert.style.height = alert.scrollHeight + "px";
      setTimeout(() => {
        alert.style.height = 0;
        alert.style.margin = 0;
        alert.style.padding = 0;
      });
      animateNode(alert, () => {
        alert.parentNode.removeChild(alert);
      });
    });
  };

  on(".alert", "click", '[data-dismiss="alert"]', (event) => {
    const alert = event.target.closest(".alert");
    closeAlert(alert);
  });
};

alerts();

if (typeof Chart !== "undefined") {
  // Colors
  let colors = {};
  colors.primary = "20, 83, 136";

  // Tooltips
  const tooltips = {
    backgroundColor: "#ffffff",
    titleFontColor: "rgba(" + colors.primary + ")",
    borderColor: "#dddddd",
    borderWidth: 0.5,
    bodyFontColor: "#555555",
    bodySpacing: 8,
    xPadding: 16,
    yPadding: 16,
    cornerRadius: 4,
    displayColors: true,
  };

  // Chart defaults
  Chart.defaults.global.defaultFontFamily = "Nunito Sans";
  Chart.defaults.global.defaultFontColor = "#555555";
  let ctx = "";

  // Charts with shadows
  const ShadowLineElement = Chart.elements.Line.extend({
    draw: function () {
      const { ctx } = this._chart;

      const originalStroke = ctx.stroke;

      ctx.stroke = function () {
        ctx.save();
        ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
        ctx.shadowBlur = 8;
        ctx.shadowOffsetX = 0;
        ctx.shadowOffsetY = 4;
        originalStroke.apply(this, arguments);
        ctx.restore();
      };

      Chart.elements.Line.prototype.draw.apply(this, arguments);

      ctx.stroke = originalStroke;
    },
  });

  Chart.defaults.lineWithShadow = Chart.defaults.line;
  Chart.controllers.lineWithShadow = Chart.controllers.line.extend({
    datasetElementType: ShadowLineElement,
  });

  Chart.defaults.radarWithShadow = Chart.defaults.radar;
  Chart.controllers.radarWithShadow = Chart.controllers.radar.extend({
    datasetElementType: ShadowLineElement,
  });

  Chart.defaults.barWithShadow = Chart.defaults.bar;
  Chart.defaults.global.datasets.barWithShadow =
    Chart.defaults.global.datasets.bar;
  Chart.controllers.barWithShadow = Chart.controllers.bar.extend({
    draw: function (ease) {
      Chart.controllers.bar.prototype.draw.call(this, ease);
      const ctx = this.chart.ctx;
      ctx.save();
      ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
      ctx.shadowBlur = 8;
      ctx.shadowOffsetX = 0;
      ctx.shadowOffsetY = 4;
      Chart.controllers.bar.prototype.draw.apply(this, arguments);
      ctx.restore();
    },
  });

  Chart.defaults.pieWithShadow = Chart.defaults.pie;
  Chart.controllers.pieWithShadow = Chart.controllers.pie.extend({
    draw: function (ease) {
      Chart.controllers.pie.prototype.draw.call(this, ease);
      const ctx = this.chart.ctx;
      ctx.save();
      ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
      ctx.shadowBlur = 8;
      ctx.shadowOffsetX = 0;
      ctx.shadowOffsetY = 4;
      Chart.controllers.pie.prototype.draw.apply(this, arguments);
      ctx.restore();
    },
  });

  Chart.defaults.doughnutWithShadow = Chart.defaults.doughnut;
  Chart.controllers.doughnutWithShadow = Chart.controllers.doughnut.extend({
    draw: function (ease) {
      Chart.controllers.doughnut.prototype.draw.call(this, ease);
      const ctx = this.chart.ctx;
      ctx.save();
      ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
      ctx.shadowBlur = 8;
      ctx.shadowOffsetX = 0;
      ctx.shadowOffsetY = 4;
      Chart.controllers.doughnut.prototype.draw.apply(this, arguments);
      ctx.restore();
    },
  });

  Chart.defaults.polarAreaWithShadow = Chart.defaults.polarArea;
  Chart.controllers.polarAreaWithShadow = Chart.controllers.polarArea.extend({
    draw: function (ease) {
      Chart.controllers.polarArea.prototype.draw.call(this, ease);
      const ctx = this.chart.ctx;
      ctx.save();
      ctx.shadowColor = "rgba(0, 0, 0, 0.25)";
      ctx.shadowBlur = 8;
      ctx.shadowOffsetX = 0;
      ctx.shadowOffsetY = 4;
      Chart.controllers.polarArea.prototype.draw.apply(this, arguments);
      ctx.restore();
    },
  });

  Chart.defaults.lineWithLine = Chart.defaults.line;
  Chart.controllers.lineWithLine = Chart.controllers.line.extend({
    draw: function (ease) {
      Chart.controllers.line.prototype.draw.call(this, ease);
      if (this.chart.tooltip._active && this.chart.tooltip._active.length) {
        const activePoint = this.chart.tooltip._active[0];
        const ctx = this.chart.ctx;
        const x = activePoint.tooltipPosition().x;
        const topY = this.chart.scales["y-axis-0"].top;
        const bottomY = this.chart.scales["y-axis-0"].bottom;
        ctx.save();
        ctx.beginPath();
        ctx.moveTo(x, topY);
        ctx.lineTo(x, bottomY);
        ctx.lineWidth = 1;
        ctx.strokeStyle = "rgba(0,0,0,0.1)";
        ctx.stroke();
        ctx.restore();
      }
    },
  });

  // Line with line
  const lineWithLinePlugin = {
    afterInit: function (chart, options) {
      const info = chart.canvas.parentNode;
      const value = chart.data.datasets[0].data[0];
      const heading = chart.data.datasets[0].label;
      const label = chart.data.labels[0];
      info.querySelector(".chart-value").innerHTML =
        "$" + value.toLocaleString();
      info.querySelector(".chart-label").innerHTML = heading + ": " + label;
    },
  };

  const lineWithLineOptions = {
    layout: {
      padding: {
        left: 5,
        right: 5,
        top: 10,
        bottom: 10,
      },
    },
    responsive: true,
    legend: {
      display: false,
    },
    tooltips: {
      intersect: false,
      enabled: false,
      custom: function (tooltipModel) {
        if (tooltipModel && tooltipModel.dataPoints) {
          const info = this._chart.canvas.parentNode;
          const value = tooltipModel.dataPoints[0].yLabel;
          const heading = tooltipModel.body[0].lines[0].split(":")[0];
          const label = tooltipModel.dataPoints[0].xLabel;
          info.querySelector(".chart-value").innerHTML =
            "$" + value.toLocaleString();
          info.querySelector(".chart-label").innerHTML = heading + ": " + label;
        }
      },
    },
    scales: {
      yAxes: [
        {
          display: false,
        },
      ],
      xAxes: [
        {
          display: false,
        },
      ],
    },
  };

  // Charts
  ctx = document.getElementById("areaChart");
  if (ctx) {
    ctx.getContext("2d");
    const areatChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "line",

      // The data for our dataset
      data: {
        labels: ["January", "February", "March", "April", "May", "June"],
        datasets: [
          {
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
            data: [5, 10, 15, 10, 15, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: false,
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("areaWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const areaChartWithShadow = new Chart(ctx, {
      // The type of chart we want to create
      type: "lineWithShadow",

      // The data for our dataset
      data: {
        labels: ["January", "February", "March", "April", "May", "June"],
        datasets: [
          {
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
            data: [5, 10, 15, 10, 15, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: false,
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("lineChart");
  if (ctx) {
    ctx.getContext("2d");
    const lineChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "line",

      // The data for our dataset
      data: {
        labels: ["January", "February", "March", "April", "May", "June"],
        datasets: [
          {
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 6,
            pointBorderWidth: 2,
            pointHoverRadius: 8,
            pointHoverBorderWidth: 2,
            fill: false,
            data: [5, 10, 15, 10, 15, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: false,
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("lineWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const lineWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "lineWithShadow",

      // The data for our dataset
      data: {
        labels: ["January", "February", "March", "April", "May", "June"],
        datasets: [
          {
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 6,
            pointBorderWidth: 2,
            pointHoverRadius: 8,
            pointHoverBorderWidth: 2,
            fill: false,
            data: [5, 10, 15, 10, 15, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: false,
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("barChart");
  if (ctx) {
    ctx.getContext("2d");
    const barChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "bar",

      // The data for our dataset
      data: {
        labels: ["一月", "二月", "三月", "四月", "五月", "六月"],
        datasets: [
          {
            label: "总收入",
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [5, 10, 15, 10, 15, 10],
          },
          {
            label: "Tomatoes",
            backgroundColor: "rgba(" + colors.primary + ", .5)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [7.5, 10, 17.5, 15, 12.5, 5],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("barWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const barWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "barWithShadow",

      // The data for our dataset
      data: {
        labels: ["一月", "二月", "三月", "四月", "五月", "六月"],
        datasets: [
          {
            label: "出货量",
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [5, 7, 13, 10, 10, 10],
          },
          {
            label: "采摘量",
            backgroundColor: "rgba(" + colors.primary + ", .5)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [7.5, 10, 17.5, 15, 12.5, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("pieChart");
  if (ctx) {
    ctx.getContext("2d");
    const pieChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "pie",

      // The data for our dataset
      data: {
        labels: ["Potatoes", "Tomatoes", "Onions"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("pieWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const pieWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "pieWithShadow",

      // The data for our dataset
      data: {
        labels: ["Potatoes", "Tomatoes", "Onions"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("doughnutChart");
  if (ctx) {
    ctx.getContext("2d");
    const doughnutChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "doughnut",

      // The data for our dataset
      data: {
        labels: ["Potatoes", "Tomatoes", "Onions"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        cutoutPercentage: 75,
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }
  ctx = document.getElementById("doughnutWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const doughnutWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "doughnutWithShadow",

      // The data for our dataset
      data: {
        labels: ["合格", "不合格"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
         
              "rgba(" + colors.primary + ", .5)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [90, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        cutoutPercentage: 75,
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }
  ctx = document.getElementById("polarChart");
  if (ctx) {
    ctx.getContext("2d");
    const polarChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "polarArea",

      // The data for our dataset
      data: {
        labels: ["Potatoes", "Tomatoes", "Onions"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        layout: {
          padding: 5,
        },
        scale: {
          ticks: {
            display: false,
          },
        },
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("polarWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const polarWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "polarAreaWithShadow",

      // The data for our dataset
      data: {
        labels: ["Potatoes", "Tomatoes", "Onions"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        layout: {
          padding: 5,
        },
        scale: {
          ticks: {
            display: false,
          },
        },
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("radarChart");
  if (ctx) {
    ctx.getContext("2d");
    const radarChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "radar",

      // The data for our dataset
      data: {
        labels: ["Drinks", "Snaks", "Lunch", "Dinner"],
        datasets: [
          {
            label: "Potatoes",
            data: [25, 25, 25, 25],
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
          },
          {
            label: "Tomatoes",
            data: [10, 10, 0, 20, 20],
            backgroundColor: "rgba(" + colors.primary + ", .25",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
          },
        ],
      },

      // Configuration options go here
      options: {
        scale: {
          ticks: {
            display: false,
            max: 30,
          },
        },
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("radarWithShadowChart");
  if (ctx) {
    ctx.getContext("2d");
    const radarWithShadowChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "radarWithShadow",

      // The data for our dataset
      data: {
        labels: ["Drinks", "Snaks", "Lunch", "Dinner"],
        datasets: [
          {
            label: "Potatoes",
            data: [25, 25, 25, 25],
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
          },
          {
            label: "Tomatoes",
            data: [10, 10, 0, 20, 20],
            backgroundColor: "rgba(" + colors.primary + ", .25",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
          },
        ],
      },

      // Configuration options go here
      options: {
        scale: {
          ticks: {
            display: false,
            max: 30,
          },
        },
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }

  ctx = document.getElementById("lineWithLine1");
  if (ctx) {
    ctx.getContext("2d");
    const lineWithLineChart1 = new Chart(ctx, {
      type: "lineWithLine",
      plugins: [lineWithLinePlugin],
      data: {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
        datasets: [
          {
            label: "总订单",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 2,
            pointBorderWidth: 4,
            pointHoverRadius: 2,
            fill: false,
            data: [1250, 1300, 1550, 900, 1800, 1100, 1600],
          },
        ],
      },
      options: lineWithLineOptions,
    });
  }

  ctx = document.getElementById("lineWithLine2");
  if (ctx) {
    ctx.getContext("2d");
    const lineWithLineChart2 = new Chart(ctx, {
      type: "lineWithLine",
      plugins: [lineWithLinePlugin],
      data: {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
        datasets: [
          {
            label: "有效订单",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 2,
            pointBorderWidth: 4,
            pointHoverRadius: 2,
            fill: false,
            data: [100, 150, 300, 200, 100, 50, 50],
          },
        ],
      },
      options: lineWithLineOptions,
    });
  }

  ctx = document.getElementById("lineWithLine3");
  if (ctx) {
    ctx.getContext("2d");
    const lineWithLineChart3 = new Chart(ctx, {
      type: "lineWithLine",
      plugins: [lineWithLinePlugin],
      data: {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
        datasets: [
          {
            label: "待处理订单",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 2,
            pointBorderWidth: 4,
            pointHoverRadius: 2,
            fill: false,
            data: [350, 400, 750, 900, 600, 50, 50],
          },
        ],
      },
      options: lineWithLineOptions,
    });
  }

  ctx = document.getElementById("lineWithLine4");
  if (ctx) {
    ctx.getContext("2d");
    const lineWithLineChart4 = new Chart(ctx, {
      type: "lineWithLine",
      plugins: [lineWithLinePlugin],
      data: {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
        datasets: [
          {
            label: "发货订单",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 2,
            pointBorderWidth: 4,
            pointHoverRadius: 2,
            fill: false,
            data: [200, 400, 250, 600, 100, 50, 50],
          },
        ],
      },
      options: lineWithLineOptions,
    });
  }

  ctx = document.getElementById("visitorsChart");
  if (ctx) {
    ctx.getContext("2d");
    const visitorsChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "lineWithShadow",

      // The data for our dataset
      data: {
        labels: ["一月", "二月", "三月", "四月", "五月", "六月"],
        datasets: [
          {
            backgroundColor: "rgba(" + colors.primary + ", .1)",
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            pointBackgroundColor: "#ffffff",
            pointBorderColor: "rgba(" + colors.primary + ")",
            pointHoverBackgroundColor: "rgba(" + colors.primary + ")",
            pointHoverBorderColor: "#ffffff",
            pointRadius: 4,
            pointBorderWidth: 2,
            pointHoverRadius: 6,
            pointHoverBorderWidth: 2,
            data: [5, 10, 15, 20, 15, 10],
          },
        ],
      },

      // Configuration options go here
      options: {
        legend: false,
        tooltips: tooltips,
        scales: {
          yAxes: [
            {
              gridLines: {
                display: true,
                drawBorder: false,
              },
              ticks: {
                stepSize: 5,
                min: 0,
                max: 20,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
      },
    });
  }

  ctx = document.getElementById("categoriesChart");
  if (ctx) {
    ctx.getContext("2d");
    const categoriesChart = new Chart(ctx, {
      // The type of chart we want to create
      type: "polarAreaWithShadow",

      // The data for our dataset
      data: {
        labels: ["白茶", "碧螺春", "西湖龙井"],
        datasets: [
          {
            backgroundColor: [
              "rgba(" + colors.primary + ", .1)",
              "rgba(" + colors.primary + ", .5)",
              "rgba(" + colors.primary + ", .25)",
            ],
            borderColor: "rgba(" + colors.primary + ")",
            borderWidth: 2,
            data: [25, 10, 15],
          },
        ],
      },

      // Configuration options go here
      options: {
        layout: {
          padding: 5,
        },
        scale: {
          ticks: {
            display: false,
          },
        },
        legend: {
          position: "bottom",
          labels: {
            usePointStyle: true,
            padding: 20,
          },
        },
        tooltips: tooltips,
      },
    });
  }
}

// Collapse
const collapse = () => {
  const selector = '[data-toggle="collapse"]';

  // Toggle Collapse
  const toggleCollapse = (event) => {
    const collapseTrigger = event.target;

    collapseTrigger.classList.toggle("active");

    // Open
    const open = (collapse) => {
      const collapseHeight = collapse.scrollHeight + "px";
      setTimeout(() => {
        collapse.style.height = collapseHeight;
        collapse.style.opacity = 1;
      }, 200);
      animateNode(collapse, () => {
        collapse.style.overflow = "visible";
        collapse.style.height = null;
        collapse.classList.add("open");
      });
    };

    // Close
    const close = (collapse) => {
      collapse.style.overflow = "hidden";
      const collapseHeight = collapse.scrollHeight + "px";
      collapse.style.height = collapseHeight;
      setTimeout(() => {
        collapse.style.height = 0;
        collapse.style.opacity = 0;
      }, 200);
      animateNode(collapse, () => {
        collapse.classList.remove("open");
      });
    };

    // Collapse
    const collapses = document.querySelectorAll(collapseTrigger.dataset.target);
    collapses.forEach((collapse) => {
      if (collapse.classList.contains("open")) {
        close(collapse);
      } else {
        open(collapse);
      }
    });

    // Accordion
    const accordion = collapseTrigger.closest(".accordion");
    if (accordion) {
      const accordionTriggers = accordion.querySelectorAll(selector);
      accordionTriggers.forEach((accordionTrigger) => {
        if (accordionTrigger !== collapseTrigger) {
          accordionTrigger.classList.remove("active");
        }
      });

      const accordions = accordion.querySelectorAll(".collapse");
      accordions.forEach((accordion) => {
        if (accordion.classList.contains("open")) {
          close(accordion);
        }
      });
    }
  };

  on("body", "click", selector, (event) => {
    toggleCollapse(event);
  });
};

collapse();

const darkMode = () => {
  const root = document.documentElement;

  const scheme = localStorage.getItem("scheme");

  const darkModeToggler = document.getElementById("darkModeToggler");

  scheme && root.classList.add(scheme);

  if (scheme === "dark") {
    if (!darkModeToggler) return;
    darkModeToggler.checked = "checked";
  }

  // Enable Dark Mode
  const enableDarkMode = () => {
    root.classList.remove("light");
    root.classList.add("dark");
    localStorage.setItem("scheme", "dark");
  };

  // Disable Dark Mode
  const disableDarkMode = () => {
    root.classList.remove("dark");
    root.classList.add("light");
    localStorage.removeItem("scheme");
  };

  // Check Dark Mode
  const checkDarkMode = (darkModeToggler) => {
    if (root.classList.contains("dark") || !darkModeToggler.checked) {
      return true;
    } else {
      return false;
    }
  };

  // Toggler
  darkModeToggler.addEventListener("change", () => {
    if (checkDarkMode(darkModeToggler)) {
      disableDarkMode();
    } else {
      enableDarkMode();
    }
  });
};

darkMode();

// Custom File Input
const customFileInput = () => {
  on("body", "change", 'input[type="file"]', (event) => {
    const filename = event.target.value.split("\\").pop();
    event.target.parentNode.querySelector(".file-name").innerHTML = filename;
  });
};

customFileInput();

// Fullscreen
const fullscreen = () => {
  const fullScreenToggler = document.getElementById("fullScreenToggler");

  if (!fullScreenToggler) return;

  const element = document.documentElement;

  // Open fullscreen
  const openFullscreen = () => {
    if (element.requestFullscreen) {
      element.requestFullscreen();
    } else if (element.mozRequestFullScreen) {
      element.mozRequestFullScreen();
    } else if (element.webkitRequestFullscreen) {
      element.webkitRequestFullscreen();
    } else if (element.msRequestFullscreen) {
      element.msRequestFullscreen();
    }
  };

  // Close fullscreen
  const closeFullscreen = () => {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitExitFullscreen) {
      document.webkitExitFullscreen();
    } else if (document.msExitFullscreen) {
      document.msExitFullscreen();
    }
  };

  // Check fullscreen
  const checkFullscreen = () => {
    if (
      document.fullscreenElement ||
      document.webkitFullscreenElement ||
      document.mozFullScreenElement ||
      document.msFullscreenElement
    ) {
      return true;
    }

    return false;
  };

  // Toggle Button Icon
  const togglerBtnIcon = () => {
    if (fullScreenToggler.classList.contains("la-expand-arrows-alt")) {
      fullScreenToggler.classList.remove("la-expand-arrows-alt");
      fullScreenToggler.classList.add("la-compress-arrows-alt");
    } else {
      fullScreenToggler.classList.remove("la-compress-arrows-alt");
      fullScreenToggler.classList.add("la-expand-arrows-alt");
    }
  };

  fullScreenToggler.addEventListener("click", () => {
    if (checkFullscreen()) {
      closeFullscreen();
    } else {
      openFullscreen();
    }

    togglerBtnIcon();
  });
};

fullscreen();

// Menu
const menu = () => {
  const menu = document.querySelector(".menu-bar");

  if (!menu) return;

  // Hide Menu Detail
  const hideMenuDetail = () => {
    menu.querySelectorAll(".menu-detail.open").forEach((menuDetail) => {
      hideOverlay();
      menuDetail.classList.remove("open");
    });
  };

  // Hide Menu - When Clicked Elsewhere
  document.addEventListener("click", (event) => {
    if (
      !event.target.closest(".menu-items a") &&
      !event.target.closest(".menu-detail")
    ) {
      hideMenuDetail();
    }
  });

  // Menu Links
  on(".menu", "click", ".menu-items a", (event) => {
    hideMenuDetail();

    const menuName = event.target.closest(".menu-items a").dataset.target;
    const selectedMenu = menu.querySelector(
      '.menu-detail[data-menu="' + menuName + '"]'
    );

    if (selectedMenu) {
      showOverlay(true);
      selectedMenu.classList.add("open");
    } else {
      hideOverlay();
    }
  });

  // Toggle Menu
  const toggleMenu = () => {
    if (menu.classList.contains("hidden")) {
      menu.classList.remove("hidden");
      document.body.classList.remove("menu-hidden");
      document.body.classList.add("menu-icon-only");
      menu.classList.add("open", "icon-only");
    } else if (menu.classList.contains("icon-only")) {
      menu.classList.remove("icon-only");
      document.body.classList.remove("menu-icon-only");
    } else {
      menu.classList.add("hidden");
      document.body.classList.add("menu-hidden");
      menu.classList.remove("open");
    }
  };

  on(".top-bar", "click", "[data-toggle='menu']", (event) => {
    toggleMenu(event);
  });
};

menu();

// Modal
const modal = () => {
  // Show
  const showModal = (modal) => {
    showOverlay();
    modal.classList.add("active");
    const animation = modal.dataset.animations.split(", ")[0];
    const modalContent = modal.querySelector(".modal-content");
    animateCSS(modalContent, animation);

    modal.addEventListener("click", (event) => {
      if (modal.dataset.staticBackdrop !== undefined) return;
      if (modal !== event.target) return;
      closeModal(modal);
    });
  };

  on("body", "click", '[data-toggle="modal"]', (event) => {
    const modal = document.querySelector(event.target.dataset.target);
    showModal(modal);
  });

  // Close
  const closeModal = (modal) => {
    hideOverlay();
    const animation = modal.dataset.animations.split(", ")[1];
    const modalContent = modal.querySelector(".modal-content");
    animateCSS(modalContent, animation, () => {
      modal.classList.remove("active");
    });
  };

  on(".modal", "click", '[data-dismiss="modal"]', (event) => {
    const modal = event.target.closest(".modal");
    closeModal(modal);
  });
};

modal();

// Overlay
// Show
const showOverlay = (workspace) => {
  if (document.querySelector(".overlay")) return;

  document.body.classList.add("overlay-show");

  const overlay = document.createElement("div");
  if (workspace) {
    overlay.setAttribute("class", "overlay workspace");
  } else {
    overlay.setAttribute("class", "overlay");
  }

  document.body.appendChild(overlay);
  overlay.classList.add("active");
};

// Hide
const hideOverlay = () => {
  overlayToRemove = document.querySelector(".overlay");

  if (!overlayToRemove) return;

  document.body.classList.remove("overlay-show");

  overlayToRemove.classList.remove("active");
  document.body.removeChild(overlayToRemove);
};

// Rating Stars
const ratingStars = () => {
  rateStars = (event) => {
    const starsContainer = event.target.closest(".rating-stars");
    const stars = Array.from(starsContainer.children);
    const totalStars = stars.length;
    const index = stars.indexOf(event.target);
    let count = 0;
    count = totalStars - index;
    stars.forEach((star) => star.classList.remove("active"));

    event.target.classList.add("active");

    console.log("You have rated " + count + " stars.");
  };

  on("body", "click", ".rating-stars", (event) => {
    rateStars(event);
  });
};

ratingStars();

// Show Password
const showPassword = () => {
  // Toggle Show Password
  const toggleShowPassword = (showPasswordBtn) => {
    const password = showPasswordBtn
      .closest(".form-control-addon-within")
      .querySelector("input");

    if (password.type === "password") {
      password.type = "text";
      showPasswordBtn.classList.remove("text-gray-600", "dark:text-gray-600");
      showPasswordBtn.classList.add("text-primary", "dark:text-primary");
    } else {
      password.type = "password";
      showPasswordBtn.classList.remove("text-primary", "dark:text-primary");
      showPasswordBtn.classList.add("text-gray-600", "dark:text-gray-600");
    }
  };

  on("body", "click", '[data-toggle="password-visibility"]', (event) => {
    const showPasswordBtn = event.target.closest(
      '[data-toggle="password-visibility"]'
    );
    toggleShowPassword(showPasswordBtn);
  });
};

showPassword();

const sidebar = () => {
  // Toggle Sidebar
  const toggleSidebar = () => {
    const sidebar = document.querySelector(".sidebar");
    if (sidebar.classList.contains("open")) {
      sidebar.classList.remove("open");
      hideOverlay();
    } else {
      sidebar.classList.add("open");
      showOverlay(true);
    }
  };

  on("body", "click", '[data-toggle="sidebar"]', () => {
    toggleSidebar();
  });
};

sidebar();

const tabs = () => {
  on("body", "click", '[data-toggle="tab"]', (event) => {
    const trigger = event.target.closest('[data-toggle="tab"]');
  
    const tabs = trigger.closest(".tabs");
    const targetedTab = tabs.querySelector(trigger.dataset.target);
    const activeTabTrigger = tabs.querySelector(".tab-nav .active");
    const activeTab = tabs.querySelector(".collapse.open");

    if (activeTabTrigger === trigger) return;

    // Trigger
    activeTabTrigger.classList.remove("active");
    trigger.classList.add("active");

    // Tab
    // Close
    activeTab.style.overflow = "hidden";
    let tabHeight = activeTab.scrollHeight + "px";
    activeTab.style.height = tabHeight;
    setTimeout(() => {
      activeTab.style.height = 0;
      activeTab.style.opacity = 0;
    }, 200);
    animateNode(activeTab, () => {
      activeTab.classList.remove("open");

      // Open
      tabHeight = targetedTab.scrollHeight + "px";
      setTimeout(() => {
        targetedTab.style.height = tabHeight;
        targetedTab.style.opacity = 1;
      }, 200);
      animateNode(targetedTab, () => {
        targetedTab.style.overflow = "visible";
        targetedTab.style.height = null;
        targetedTab.classList.add("open");
      });
    });
  });
};

tabs();

// Tippy
const customTippy = () => {
  // Menu tooltip
  tippy.delegate("body", {
    target: '.icon-only [data-toggle="tooltip-menu"]',
    touch: ["hold", 500],
    theme: "light-border tooltip",
    offset: [0, 12],
    interactive: true,
    animation: "scale",
    placement: "right",
    appendTo: () => document.body,
  });

  // General tooltip
  tippy('[data-toggle="tooltip"]', {
    theme: "light-border tooltip",
    touch: ["hold", 500],
    offset: [0, 12],
    interactive: true,
    animation: "scale",
  });

  // Popover
  tippy('[data-toggle="popover"]', {
    theme: "light-border popover",
    offset: [0, 12],
    interactive: true,
    allowHTML: true,
    trigger: "click",
    animation: "shift-toward-extreme",
    content: (reference) => {
      const title = reference.dataset.popoverTitle;
      const content = reference.dataset.popoverContent;
      const popover =
        "<h5>" + title + "</h5>" + '<div class="mt-5">' + content + "</div>";
      return popover;
    },
  });

  // Dropdown
  tippy('[data-toggle="dropdown-menu"]', {
    theme: "light-border",
    zIndex: 45,
    offset: [0, 8],
    arrow: false,
    placement: "bottom-start",
    interactive: true,
    allowHTML: true,
    animation: "shift-toward-extreme",
    content: (reference) => {
      let dropdownMenu = reference
        .closest(".dropdown")
        .querySelector(".dropdown-menu");
      dropdownMenu = dropdownMenu.outerHTML;
      return dropdownMenu;
    },
  });

  // Custom Dropdown
  tippy('[data-toggle="custom-dropdown-menu"]', {
    theme: "light-border",
    zIndex: 45,
    offset: [0, 8],
    arrow: false,
    placement: "bottom-start",
    interactive: true,
    allowHTML: true,
    animation: "shift-toward-extreme",
    content: (reference) => {
      let dropdownMenu = reference
        .closest(".dropdown")
        .querySelector(".custom-dropdown-menu");
      dropdownMenu = dropdownMenu.outerHTML;
      return dropdownMenu;
    },
  });

  // Search & Select
  tippy('[data-toggle="search-select"]', {
    theme: "light-border",
    offset: [0, 8],
    maxWidth: "none",
    arrow: false,
    placement: "bottom-start",
    trigger: "click",
    interactive: true,
    allowHTML: true,
    animation: "shift-toward-extreme",
    content: (reference) => {
      let dropdownMenu = reference
        .closest(".search-select")
        .querySelector(".search-select-menu");
      dropdownMenu = dropdownMenu.outerHTML;
      return dropdownMenu;
    },
    appendTo(reference) {
      return reference.closest(".search-select");
    },
  });
};

customTippy();

// Toasts

const toasts = () => {
  const toastsContainer = document.getElementById("toasts-container");

  const toastCloseSelector = '[data-dismiss="toast"]';

  // Toast
  const createToast = (toast) => {
    const title = toast.dataset.title;
    const content = toast.dataset.content;
    const time = toast.dataset.time;
    let newToast =
      '<div class="toast">' +
      '<div class="toast-wrapper mb-5">' +
      '<div class="toast-header">' +
      "<h5>" +
      title +
      "</h5>" +
      "<small>" +
      time +
      "</small>" +
      '<button type="button" class="close" data-dismiss="toast">&times</button>' +
      "</div>" +
      '<div class="toast-body">' +
      content +
      "</div>" +
      "</div>" +
      "</div>";

    newToast = new DOMParser().parseFromString(newToast, "text/html").body
      .firstChild;

    newToast.querySelector(toastCloseSelector).addEventListener("click", () => {
      closeToast(newToast);
    });

    toastsContainer.appendChild(newToast);
    animateCSS(newToast, "fadeInUp");
  };

  on("body", "click", '[data-toggle="toast"]', (event) => {
    const toast = event.target;
    createToast(toast);
  });

  // Close Toast
  const closeToast = (toast) => {
    animateCSS(toast, "fadeOutUp", () => {
      toast.style.opacity = 0;
      toast.style.overflow = "hidden";
      const toastHeight = toast.scrollHeight + "px";
      toast.style.height = toastHeight;
      setTimeout(() => {
        toast.style.height = 0;
      });
      animateNode(toast, () => {
        toast.parentNode.removeChild(toast);
      });
    });
  };

  on("body", "click", toastCloseSelector, (event) => {
    toast = event.target.closest(".toast");
    closeToast(toast);
  });
};

toasts();
