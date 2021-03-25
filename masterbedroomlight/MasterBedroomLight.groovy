definition(
    name: "MasterBedroomLight",
    namespace: "awesomeland",
    author: "Kurtis Story",
    description: "Turn on Lights when Switch hit",
    category: "Light",
    iconUrl: "",
    iconX2Url: "")

preferences {
	section("Inputs") {
		input "switches", "capability.switch", title: "Select Switch", submitOnChange: true, required: true, multiple: true
        input "lights", "capability.light", title: "Select Lights", submitOnChange: true, required: true, multiple: true
	}
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(switches, "switch", handler)
}

def handler(evt) {
    if (evt.value == "on")
    {
        log.info "Lights On"
    	for (light in lights) {
            light.on()
        }
	}
    else
    {
        log.info "Lights Off"
        for (light in lights) {
            light.off()
        }
    }
}
