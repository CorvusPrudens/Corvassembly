sysvars = [
    ["ram", "UART", "0"],
    ["ram", "UART_STATUS", "1"],
    ["ram", "DISPLAY", "36"],
    ["ram", "R4000", "2"],
    ["ram", "R4001", "3"],
    ["ram", "R4002", "4"],
    ["ram", "R4003", "5"],
    ["ram", "R4004", "6"],
    ["ram", "R4005", "7"],
    ["ram", "R4006", "8"],
    ["ram", "R4007", "9"],
    ["ram", "R4008", "10"],
    ["ram", "R4009", "11"],
    ["ram", "R400A", "12"],
    ["ram", "R400B", "13"],
    ["ram", "R400C", "14"],
    ["ram", "R400D", "15"],
    ["ram", "R400E", "16"],
    ["ram", "R400F", "17"],
    ["ram", "R4010", "18"],
    ["ram", "R4011", "19"],
    ["ram", "R4012", "20"],
    ["ram", "R4013", "21"],
    ["ram", "R4014", "22"],
    ["ram", "R4015", "23"],
    ["ram", "R4016", "24"],
    ["ram", "R4017", "25"],
    ["ram", "R9000", "26"],
    ["ram", "R9001", "27"],
    ["ram", "R9002", "28"],
    ["ram", "R9003", "29"],
    ["ram", "RA000", "30"],
    ["ram", "RA001", "31"],
    ["ram", "RA002", "32"],
    ["ram", "RB000", "33"],
    ["ram", "RB001", "34"],
    ["ram", "RB002", "35"],
    ["ram", "TIMER_STATUS", "37"],
    ["ram", "TIMER_COMP", "38"],
    ["ram", "TIMER_PRES", "39"],
    ["ram", "TIMER1_COMP", "40"],
    ["ram", "TIMER1_PRES", "41"],
    # ['ram', 'SCOPE_RATE', '42'],
    # ['ram', 'SCOPE_ADDR', '43'],
    ["ram", "SCOPE_DATA", "42"],
    ["ram", "SCOPE_TRIGGER", "44"],
    ["ram", "FLASH_DATA", "45"],
    ["ram", "FLASH_PAGE", "46"],
    ["ram", "FLASH_STATUS", "47"],
    # ["ram", "BUTT", "48"],
    ["ram", "BOOT", "48"],

    ['ram', 'A', '56'],
    ['ram', 'B', '57'],
    ['ram', 'X', '58'],
    ['ram', 'Y', '59'],
    ['ram', 'UP', '60'],
    ['ram', 'DOWN', '61'],
    ['ram', 'LEFT', '62'],
    ['ram', 'RIGHT', '63'],

    ["pre", "FLASH_WRITE_WORD", "8"],
    ["pre", "FLASH_READ_WORD", "4"],
    ["pre", "FLASH_ERASE_WORD", "16"],
    ["pre", "TIMER0_EN", "1"],
    ["pre", "TIMER1_EN", "2"],
    ["pre", "TX_EMPTY", "2"],
    ["pre", "TX_FULL", "1"],
    ["pre", "RX_EMPTY", "8"],
    ["pre", "RX_FULL", "4"],
]

keywords = [
    'a', 'b', 'c', 'd',
    'e', 'f', 'g', 'h',
    'pre', 'ram', 'rom', 'gpu',
    'zero', 'carry', 'negative', 'as',
    'for', 'equal', 'greater', 'less',
    'SCOPE_RATE', 'UART', 'UART_STATUS', 'STACK', 'STATUS',
    'SCOPE_ADDR', 'GPIO', 'GPIO_DIR', 'TX_EMPTY',
    'SCOPE_DATA', 'TX_FULL', 'RX_EMPTY', 'RX_FULL',
    'SCOPE_TRIGGER', 'continue', 'break', 'breakall', 'FLASH_DATA',
    'FLASH_READ', 'FLASH_WRITE', 'FLASH_STATUS', 'FLASH_PAGE',
    'FLASH_WRITE_WORD', 'FLASH_READ_WORD', 'FLASH_ERASE_WORD', 'TIMER',
    'TIMER_COMP', 'TIMER_PRES', 'TIMER_STATUS',
    'BUTT', 'BOOT',
    'A', 'B', 'X', 'Y', 'UP', 'DOWN', 'LEFT', 'RIGHT',
    'FRAME', 'R4000', 'R4001', 'R4002',
    'R4003', 'R4004', 'R4005', 'R4006',
    'R4007', 'R4008', 'R4009', 'R400A',
    'R400B', 'R400C', 'R400D', 'R400E',
    'R400F', 'R4010', 'R4011', 'R4012',
    'R4013', 'R4014', 'R4015', 'R4016',
    'R4017', 'R9000', 'R9001', 'R9002',
    'R9003', 'RA000', 'RA001', 'RA002',
    'RB000', 'RB001', 'RB002',
]
