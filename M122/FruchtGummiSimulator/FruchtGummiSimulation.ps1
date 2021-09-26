# --------------------------------------------
# Datei:            FruchtGummiSimulation.ps1
# Datum:            10.09.2021
# Ersteller:        Niklas Sidler & Kylian Lichtensteiger (BSI AG)
# Version:          69.4.20
# Änderungen:       10.09.2021/CSV Speicher Bug behoben
# Beschreibung:
# 69
# --------------------------------------------
[String]$global:CSVBuffer = """Config Index"",""Path Length"",""Amount Fruit Gum Sorts"",""Amount Fruit Gums per Sort"",""AVG Throws"",""Iterations"",""Player Win Chance""`n`r"
[Int32]$global:CSVLineCount = 1

function saveStatsToBuffer {
    param (
        [Int32]$PathLength,
        [Int32]$AmountFruitGumSorts,
        [Int32]$AmountFruitGumsPerSort,
        [Int32]$AVGDiceRollCount,
        [Int32]$IterationCount,
        [Int32]$WinChance
    )
#    Write-Host $PathLength
#    Write-Host $AmountFruitGumSorts
#    Write-Host $AmountFruitGumsPerSort
#    Write-Host $AVGDiceRollCount
#    Write-Host $IterationCount
#    Write-Host $WinChance
    $global:CSVBuffer += """" + [String]$CSVLineCount + """," +
            """" + [String]$PathLength + """," +
            """" + [String]$AmountFruitGumSorts + """," +
            """" + [String]$AmountFruitGumsPerSort + """," +
            """" + [String]$AVGDiceRollCount + """," +
            """" + [String]$IterationCount + """," +
            """" + [String]$WinChance + """`n`r"
    $global:CSVLineCount++
}
function saveBufferToCSV {
    New-Item ([String](Get-Location) + "\ConfigData.csv") -ItemType File
    Set-Content ([String](Get-Location)+ "\ConfigData.csv") $global:CSVBuffer
}
function readInteger{
    param (
        [String]$promptMessage,
        [Int32]$boundaryMin,
        [Int32]$boundaryMax
    )
    $inputValid = $false
    do
    {
        $promptInput = Read-host -Prompt $promptMessage
        try
        {
            $parsedInput = [convert]::ToInt32($promptInput)
            if ($parsedInput -ge $boundaryMin -and $parsedInput -le $boundaryMax) {
                $inputValid = $true
            } else {
                throw 'Value not in Range'
            }
        }
        catch
        {
            Clear-Host
            Write-Host "The Input was not a Valid Number! `nPlease enter a number between: " $boundaryMin " and " $boundaryMax " `nTry Again!`n"
        }
    } while ($inputValid -eq $false)
    Clear-Host
    return $parsedInput
}

function initArray {

    param (
        [Int32]$arrayLength,
        [Int32]$arrayValue
    )
    [int32[]]$arr = 0..($arrayLength-1)
    for ($i = 0; $i -lt $arr.Count; $i++) {
        $arr[$i] = $arrayValue
    }
    return $arr
}

function sumArray {
    param (
        [Int32[]]$arr
    )
    foreach ($value in $arr)
    {
        $sum += $value
    }
    return $sum
}

function playGame {
    param (
        [Int32]$GhostStepsLeft,
        [Int32]$AmountFruitGumsPerSort,
        [Int32]$AmountFruitGumSorts
    )
    $DiceRollCount = 0
    $FruitGumArray = initArray $AmountFruitGumSorts $AmountFruitGumsPerSort
    while ((sumArray $FruitGumArray -gt 0) -and ($GhostStepsLeft -gt 0))
    {
        $DiceRollCount++
        $num = Get-Random -Maximum ($AmountFruitGumSorts+1)
#        Write-Host "Random number: " $num
        if ($num -eq $AmountFruitGumSorts) {
            $GhostStepsLeft--
        } else {
            if ($FruitGumArray[$num] -gt 0) {
                $FruitGumArray[$num]--
            }
        }
#        Write-Host "Array: " $FruitGumArray
#        Write-Host "Ghost steps: " $GhostStepsLeft
#        Write-Host "`n"
    }
#    Write-Host "Dice Rolls: " $DiceRollCount " Ghost Steps Left: " $GhostStepsLeft " Array: " $FruitGumArray
    if ($GhostStepsLeft -gt 0) {
        $win = 1
    } else {
        $win = 0
    }
    return @($DiceRollCount,$win)
}

function playNGames {
    param (
        [Int32]$GhostStepsLeft,
        [Int32]$AmountFruitGumsPerSort,
        [Int32]$AmountFruitGumSorts,
        [Int32]$IterationCount
    )
    $TotalDiceRolls = 0
    $TotalWins = 0
    for ($i = 0; $i -lt $IterationCount; $i++) {
        [Int32[]]$arr = playGame $GhostStepsLeft $AmountFruitGumsPerSort $AmountFruitGumSorts
        $TotalDiceRolls += $arr[0]
        $TotalWins += $arr[1]
    }
    saveStatsToBuffer $GhostStepsLeft $AmountFruitGumSorts $AmountFruitGumsPerSort ($TotalDiceRolls/$IterationCount) $IterationCount (($TotalWins/$IterationCount)*100)
}

function readConfigInput {
    $AmountFruitGumSorts = readInteger 'Enter the Number of the Fruit Gum sorts' 2 100
    $AmountFruitGumsPerSort = readInteger 'Enter the Amount of Gums per sort' 2 10
    $LengthPath = readInteger 'Enter the path length' 6 30
    $AmountIterations = readInteger 'Enter the iteration count' 1 1000
    return @($AmountFruitGumSorts,$AmountFruitGumsPerSort,$LengthPath,$AmountIterations)
}

Clear-Host
Write-Host "Welcum to the best Fruit Gum Goat Simulator v.69.4.20`n"
do
{
    $arr = readConfigInput
    playNGames $arr[2] $arr[1] $arr[0] $arr[3]
    Write-Host $CSVBuffer
    $choosen = readInteger "Chose Option:`n1 Add new Configuration`n2 Close without saving CSV`n3 Close with saving CSV`n" 1 3
} while ($choosen -eq 1)
if ($choosen -eq 3) {
    saveBufferToCSV
}